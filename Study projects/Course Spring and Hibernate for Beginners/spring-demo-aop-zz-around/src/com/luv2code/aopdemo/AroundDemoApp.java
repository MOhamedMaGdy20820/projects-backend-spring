package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		TrafficFortuneService theFortuneService = 
				context.getBean("trafficFortuneService",TrafficFortuneService.class);
		
		System.out.println("\nMain program: AroundDemoApp");
		System.out.println("Calling getFortune");
		
		String data = theFortuneService.getFourtune();
		
		System.out.println("\nMy fortune is: "+data);
		
		System.out.println("Finished");
		
		
		// close the context
		context.close();

	}

}

































