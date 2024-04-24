package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // provide similar support to <mvc:ammotation-driven>
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo" )
public class DemoAppConfig {

	// define a bean for ViewResolver
	@Bean
	public ViewResolver ViewResolver() {
		InternalResourceViewResolver viewResolver  = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	
	
}
