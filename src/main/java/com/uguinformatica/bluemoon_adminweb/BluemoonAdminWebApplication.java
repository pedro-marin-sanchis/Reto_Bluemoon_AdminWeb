package com.uguinformatica.bluemoon_adminweb;

import com.uguinformatica.bluemoon_adminweb.service.APIValues;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BluemoonAdminWebApplication {
	public static void main(String[] args) {
		// ARGUMENTS
		String apiUrl = System.getenv("API_URL");
		String appUserUsername = System.getenv("APP_USER_USERNAME");
		String appUserPassword = System.getenv("APP_USER_PASSWORD");
		System.out.println(System.getenv("API_URL"));
		if (apiUrl != null && !apiUrl.isEmpty()) { APIValues.API_URL = apiUrl; }
		if (appUserUsername != null && !appUserUsername.isEmpty()) { APIValues.APP_USER_USERNAME = appUserUsername; }
		if (appUserPassword != null && !appUserPassword.isEmpty()) { APIValues.APP_USER_PASSWORD = appUserPassword; }

		// Start Spring Boot application
		SpringApplication.run(BluemoonAdminWebApplication.class, args);
	}
}
