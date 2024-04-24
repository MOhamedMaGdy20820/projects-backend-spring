package com.luv2code.springdemo.mvc;

import java.util.LinkedHashMap;

public class Student {
	
	private String firstName;
	private String lastName;
	private String country;
	private String favoraiteLanguage;
	private String[] operatingSystem;

				   
	private LinkedHashMap<String, String> countryOptons;
	
	public Student() {
		
		// populate country option : used ISO country code
		
		countryOptons = new LinkedHashMap<>();
		countryOptons.put("BR", "Brazil");
		countryOptons.put("FR", "France");
		countryOptons.put("DE", "Germany");
		countryOptons.put("IN", "India");
		countryOptons.put("EG", "Egypt");

		
	
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LinkedHashMap<String, String> getCountryOptons() {
		return countryOptons;
	}
	public String getFavoraiteLanguage() {
		return favoraiteLanguage;
	}
	public void setFavoraiteLanguage(String favoraiteLanguage) {
		this.favoraiteLanguage = favoraiteLanguage;
	}
	public String[] getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String[] operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	
	
	

}
