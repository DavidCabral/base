package br.com.modelo.config;

import br.com.modelo.entity.User;
import br.com.modelo.repository.SQL;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "transactionManager",
        basePackageClasses = SQL.class, enableDefaultTransactions = false)
@EnableTransactionManagement
public class DataSourceConfig {

    private final Environment env;

    @Autowired
    public DataSourceConfig(Environment env) {
        this.env = env;
    }


    @Bean(destroyMethod = "close")
    public DataSource dt() {
        return new HikariDataSource(getHikariConfig());
    }


    @Bean(name = "entityManager")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan(User.class.getPackage().getName());
        factory.setDataSource(dt());
        factory.afterPropertiesSet();
        return factory.getObject();
    }


    private HikariConfig getHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("driver"));
        hikariConfig.setUsername(env.getProperty("user"));
        hikariConfig.setPassword(env.getProperty("pass"));
        hikariConfig.setJdbcUrl(env.getProperty("url")+"?createDatabaseIfNotExist=true&useSSL=false");
        int nPool = Integer.parseInt(env.getProperty("maximumPoolSize", "5"));
        hikariConfig.setMaximumPoolSize(nPool);
        return hikariConfig;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }


    
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
