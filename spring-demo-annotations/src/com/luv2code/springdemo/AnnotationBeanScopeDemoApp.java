package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
	
//		load spring config file
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
//		retrieve bean from sspring container
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("tennisCoach", Coach.class);
		
//		check if they are the same 
		System.out.println("\nPointing to the same object   : " + (  theCoach == alphaCoach));

		System.out.println("\nMemory location for theCoach  : " +  theCoach);
		
		System.out.println("\nMemory location for alphaCoach: " +alphaCoach);
		
		context.close();
	}

}
