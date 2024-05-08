 package com.api.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@EnableConfigurationProperties
@EnableEncryptableProperties

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.api"})

public class ApiMainApplication extends SpringBootServletInitializer
{
	
	
	
	
    @SuppressWarnings("unused")
	private static ConfigurableApplicationContext run;

	public static void main(String[] args) 
    {
		 run = SpringApplication.run(ApiMainApplication.class, args);		
	}
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(ApiMainApplication.class);
	 }
	

}
