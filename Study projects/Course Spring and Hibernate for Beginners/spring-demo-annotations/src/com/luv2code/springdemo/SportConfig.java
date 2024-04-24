package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration					// to make this file --> spring config file
//@ComponentScan("com.luv2code.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfig {

//	define bean for our sad fortune service	(class)
	@Bean
	public Coach swimCoach() {	// method name will be the bean id , swimCoach: method
		return new SwimCoach(sadFortuneService());	// swimCoach: constructor
	}
	
//	define bean for our swim coach			(class)
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
}
