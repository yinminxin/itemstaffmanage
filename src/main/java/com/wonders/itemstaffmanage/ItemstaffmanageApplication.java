package com.wonders.itemstaffmanage;

import com.wonders.itemstaffmanage.vo.Qiniu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItemstaffmanageApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ItemstaffmanageApplication.class);
	}

	@Bean
	@ConfigurationProperties(prefix = "qiniu")
	public Qiniu qiniuProperties(){
		return new Qiniu();
	}


	public static void main(String[] args) {
		SpringApplication.run(ItemstaffmanageApplication.class, args);
	}
}
