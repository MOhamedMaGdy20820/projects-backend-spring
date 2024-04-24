package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {
	
	public String getFourtune() {
		
		// simulate a delay
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("2. in getFourtune");
		
		// return a fortune
		return "Expect heavy traffid this morning";
		
	}

}
