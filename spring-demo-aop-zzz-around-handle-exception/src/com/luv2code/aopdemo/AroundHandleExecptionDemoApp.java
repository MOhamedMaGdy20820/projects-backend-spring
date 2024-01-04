package com.luv2code.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundHandleExecptionDemoApp {

	private static Logger myLogger =
				Logger.getLogger(AroundHandleExecptionDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		TrafficFortuneService theFortuneService = 
				context.getBean("trafficFortuneService",TrafficFortuneService.class);
		
		myLogger.info("\nMain program: AroundDemoApp");
		myLogger.info("Calling getFortune");
		
		boolean tripWire = true;
		String data =  null;
		
		try {
			data = theFortuneService.getFourtune(tripWire);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		myLogger.info("\nMy fortune is: "+data);
		
		myLogger.info("Finished");
		
		
		// close the context
		context.close();

	}

}

































