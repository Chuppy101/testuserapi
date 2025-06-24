package com.example.testuserapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestuserapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestuserapiApplication.class, args);
	}

}
