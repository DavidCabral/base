package br.com.modelo.config;

import br.com.modelo.entidades.Usuario;
import br.com.modelo.repositorio.SQL;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        transactionManagerRef = "trasctionManager",
        basePackageClasses = SQL.class, enableDefaultTransactions = false)
@EnableTransactionManagement
public class DataSourceConfig {


    @Bean(destroyMethod = "close")
    public DataSource dtPosseidon() {        
        return new HikariDataSource(getHikariConfig());
    }


    @Bean(name = "entityManager")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan(Usuario.class.getPackage().getName());
        factory.setDataSource(dtPosseidon());
        factory.afterPropertiesSet();
        return factory.getObject();
    }


    HikariConfig getHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
        hikariConfig.setUsername("SYSDBA");
        hikariConfig.setPassword("masterkey");
        hikariConfig.setMaximumPoolSize(5);
        return hikariConfig;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        bean.setDatabase(Database.H2);
        bean.setGenerateDdl(true);
        return bean;
    }


    
    @Bean(name = "trasctionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
