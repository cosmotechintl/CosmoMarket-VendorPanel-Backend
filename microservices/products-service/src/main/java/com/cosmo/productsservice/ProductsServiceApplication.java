package com.cosmo.productsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EntityScan(basePackages = {"com.cosmo"})
@EnableJpaRepositories(basePackages = {"com.cosmo"})
@ComponentScan(basePackages = {"com.cosmo"})
@SpringBootApplication(scanBasePackages = "com.cosmo")
public class ProductsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }

}
