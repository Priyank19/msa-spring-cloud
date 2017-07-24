package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class APIServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(APIServiceApplication.class, args);
	}
}
