package br.com.modelo.config;

import br.com.modelo.Application;
import br.com.modelo.gui.Systray;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
@PropertySource(value = {"classpath:my.properties"})
@PropertySource(value = {".external.properties"}, ignoreResourceNotFound = true)
public class Config {
    @Autowired private Environment env;
    private int porta = 5555;


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        if (env.getProperty("porta") != null && !env.getProperty("porta").isEmpty()) {
            porta = Integer.parseInt(env.getProperty("porta"));
        }
        return (container -> container.setPort(porta));
    }


    @Bean
    public Systray frame() {
        return new Systray();
    }


    public int getPorta() {
        return porta;
    }



}
