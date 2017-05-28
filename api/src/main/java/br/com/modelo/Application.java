package br.com.modelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        applicationContext = new SpringApplicationBuilder(Application.class)
                .headless(false)
                .run(args);
    }


}
