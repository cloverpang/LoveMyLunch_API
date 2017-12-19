package com.lovemylunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class LoveMyLunchBackendApiApplication {

	@RequestMapping("/")
	public String index(){
		return "Hello This is APIs! ";
	}

	public static void main(String[] args) {
		SpringApplication.run(LoveMyLunchBackendApiApplication.class, args);
	}
}
