package com.cosmo.vendorservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.cosmo", exclude = FreeMarkerAutoConfiguration.class)
@EnableTransactionManagement
@EntityScan(basePackages = {"com.cosmo"})
@EnableJpaRepositories(basePackages = {"com.cosmo"})
@ComponentScan(basePackages = {"com.cosmo"})
@EnableWebMvc
public class VendorServiceApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(VendorServiceApplication.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(VendorServiceApplication.class, args);
        String mysqlUri = context.getEnvironment().getProperty("spring.datasource.url");
        LOG.info("Connected to MySQL: " + mysqlUri);
    }

}
