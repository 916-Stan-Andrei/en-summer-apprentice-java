package com.practica.demoPractica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DemoPracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoPracticaApplication.class, args);
	}

}
