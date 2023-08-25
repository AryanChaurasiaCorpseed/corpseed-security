package com.corpseed.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
@Author :- Aryan Chaurasia

*/	 
@SpringBootApplication
@EnableDiscoveryClient
public class CorpseedSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorpseedSecurityApplication.class, args);
	}

}
