package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;


// target object
@Component
public class MembershipDAO {
	
	public boolean addSillyMember() {
		System.out.println( getClass() + ": DOING MY DV WORK: ADDING A MEMBERSHIP ACCOUNT");
		//           gives us the class name for display
		return true;
	}
	
	public boolean goToSleep() {
		System.out.println(getClass() + ": goToSleep()");
		return false;
	}
	
}




























