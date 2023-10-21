package com.rnp.blog_app_apis;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
		System.out.print("working....");
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
