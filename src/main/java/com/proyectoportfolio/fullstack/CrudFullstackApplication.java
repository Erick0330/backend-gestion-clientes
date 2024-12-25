package com.proyectoportfolio.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CrudFullstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudFullstackApplication.class, args);
//
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String rawPassword = "admin";
//		String encodedPassword = encoder.encode(rawPassword);
//		System.out.println(encodedPassword);
	}

}
