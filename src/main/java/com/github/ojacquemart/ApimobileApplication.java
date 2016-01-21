package com.github.ojacquemart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApimobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApimobileApplication.class, args);
	}
}
