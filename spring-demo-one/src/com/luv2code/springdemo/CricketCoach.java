package com.luv2code.springdemo;

public class CricketCoach implements Coach {

	private FortuneService  fortuneService;		// object
	
//	add new fields for emailaddress and team
	private String emailAddress;
	private String team;
	
//	create no-arg constructor
	public CricketCoach() {
		System.out.println("CricketCoach: inside no-arg constructor");
	}
	
	
//---------- our setter method (the dependency) & getter s------------------------------------------
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("CricketCoach: inside setter method - setFortuneService");
		this.fortuneService = fortuneService;
	}
	

	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		System.out.println("CricketCoach: inside setter method - setEmailAddress");
		this.emailAddress = emailAddress;
	}


	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		System.out.println("CricketCoach: inside setter method - setTaem");
		this.team = team;
	}


	@Override
	public String getDailyWorkout() {
		return "practicr fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
