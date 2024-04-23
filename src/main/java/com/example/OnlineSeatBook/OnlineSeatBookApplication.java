package com.example.OnlineSeatBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class
OnlineSeatBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineSeatBookApplication.class, args);
	}

}
