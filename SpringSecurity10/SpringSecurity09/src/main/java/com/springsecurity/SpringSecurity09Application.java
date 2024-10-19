package com.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SpringSecurity09Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity09Application.class, args);
	}

}
