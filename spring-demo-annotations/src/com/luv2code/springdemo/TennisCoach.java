package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
public class TennisCoach implements Coach {

//		field injection
	@Autowired
	@Qualifier("happyFortuneService")
	private FortuneService fortuneService;			// field
	
	@Value("$foo.email")
	private String email;
	
	@Value("$foo.team")
	private String team;

// 			constructor injection	
	/*
	@Autowired
	public TennisCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	*/
	
//			define a default constructor
	@Autowired
	public TennisCoach() {
		System.out.println(">> TennisCoach: inside default constructor");
	}
	
//	define my init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println(">> TennisCoach: inside of doMyStartupStuff()");
	}
	
//	define my destroy method
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println(">> TennisCoach: inside of doMyCleanupStuff()");
	}
	
//			define a setter method
	/*
	@Autowired
	public void setForuneService(FortuneService fortuneService) {
		System.out.println(">> TennisCoach: inside setForuneService() method");

		this.fortuneService = fortuneService;
	}
	*/
	
	
	/*
	@Autowired
	public void doSomeCrazyStuff(FortuneService fortuneService) {
		System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");

		this.fortuneService = fortuneService;
	}
	*/
	
	@Override
	public String getDailyWorkout() {
		return "get daily work out";
	}
	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}
}
