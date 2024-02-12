package com.uguinformatica.bluemoon_adminweb;

import com.uguinformatica.bluemoon_adminweb.service.APIValues;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BluemoonAdminWebApplication {
	public static void main(String[] args) {
		// ARGUMENTS
		APIValues.API_URL = System.getenv("API_URL");
		APIValues.APP_USER_USERNAME = System.getenv("APP_USER_USERNAME");
		APIValues.APP_USER_PASSWORD = System.getenv("APP_USER_PASSWORD");

		// Start Spring Boot application
		SpringApplication.run(BluemoonAdminWebApplication.class, args);
	}
}
