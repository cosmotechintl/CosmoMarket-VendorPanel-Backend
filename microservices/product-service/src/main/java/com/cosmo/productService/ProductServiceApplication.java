package com.cosmo.productService;

import com.cosmo.authentication.core.config.ApplicationConfig;
import com.cosmo.vendorservice.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({WebSecurityConfig.class, ApplicationConfig.class})
@SpringBootApplication(scanBasePackages = "com.cosmo")
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
