package com.example.reactiveprogramspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
//@EnableConfigurationProperties
//@ComponentScans(value = {
//		@ComponentScan("com.example.reactiveprogramspring.repository"),
//		@ComponentScan("com.example.reactiveprogramspring.service"),
//})
public class ReactiveProgramSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReactiveProgramSpringApplication.class, args);
	}

}
