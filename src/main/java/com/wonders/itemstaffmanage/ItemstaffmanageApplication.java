package com.wonders.itemstaffmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ItemstaffmanageApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ItemstaffmanageApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ItemstaffmanageApplication.class, args);
	}
}
