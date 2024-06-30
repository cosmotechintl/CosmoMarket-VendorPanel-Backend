package com.cosmo.futsalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.cosmo")
@EntityScan(basePackages = {"com.cosmo"})
@EnableJpaRepositories(basePackages = {"com.cosmo"})
@ComponentScan(basePackages = {"com.cosmo"})
@EnableWebMvc
public class FutsalServiceApplication extends SpringBootServletInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(FutsalServiceApplication.class);


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FutsalServiceApplication.class, args);
		String mysqlUri = context.getEnvironment().getProperty("spring.datasource.url");
		LOG.info("Connected to MySQL: " + mysqlUri);
	}

}