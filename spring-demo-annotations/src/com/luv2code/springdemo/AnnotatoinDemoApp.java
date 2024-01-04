package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotatoinDemoApp {
	public static void main(String[] args) {
//		read spring config file
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
				
		
//		get the bean from spring container 
		Coach theCoach = context.getBean("tennisCoach", Coach.class); // ("id", type)
		
//		call method on the bean 
		System.out.println(theCoach.getDailyWorkout());
		
//		call method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		context.close(); 
		
	}

}
