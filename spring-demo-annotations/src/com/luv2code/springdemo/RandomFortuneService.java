package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
//			create an array of strings
	private String[] data = {
			"Beware of the wolf in shepp's closing !!!",
			"you are lucky today :)",
			"this is a bad Fortune :("
	};
//	create a random number generator
	private Random myRandom = new Random();
	

	@Override
	public String getFortune() {
		int index = myRandom.nextInt(data.length);
		
		String theFortune = data[index];
	
		return theFortune;
	}

}
