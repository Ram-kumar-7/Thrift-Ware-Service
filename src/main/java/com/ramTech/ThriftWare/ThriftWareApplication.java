package com.ramTech.ThriftWare;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ThriftWareApplication {

	@Value("${PORT:10101}")
    private String port;
	public static void main(String[] args) {
		SpringApplication.run(ThriftWareApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("Application is running on port: " + System.getenv("PORT"));
	}

}
