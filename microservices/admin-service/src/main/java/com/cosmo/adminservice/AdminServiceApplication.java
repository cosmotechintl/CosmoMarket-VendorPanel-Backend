package com.cosmo.adminservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.cosmo")
public class AdminServiceApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(AdminServiceApplication.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AdminServiceApplication.class, args);
        String mysqlUri = context.getEnvironment().getProperty("spring.datasource.url");
        LOG.info("Connected to MySQL: " + mysqlUri);
    }

}
