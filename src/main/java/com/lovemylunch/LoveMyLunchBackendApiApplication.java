package com.lovemylunch;

import com.lovemylunch.common.util.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
public class LoveMyLunchBackendApiApplication extends SpringBootServletInitializer {

	@RequestMapping("/")
	public String index(){
		return "Hello This is APIs! ";
	}

	@RequestMapping("/admin/flushAllRedis")
	public String flushAllRedis(){
		try{
			RedisUtil.flushAll();
			return "flushAllRedis success! ";
		}catch (Exception e){
			return "flushAllRedis failed : " + e.getMessage();
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LoveMyLunchBackendApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(LoveMyLunchBackendApiApplication.class, args);
	}
}
