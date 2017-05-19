package com.niit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemInfoApplication.class, args);
	}
}
