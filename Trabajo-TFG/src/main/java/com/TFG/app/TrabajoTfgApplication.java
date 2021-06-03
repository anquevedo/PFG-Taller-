package com.TFG.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// esta barra es para quitar la seguridad de la app. Sin esto se quedaria con @SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class TrabajoTfgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabajoTfgApplication.class, args);
	}

}
