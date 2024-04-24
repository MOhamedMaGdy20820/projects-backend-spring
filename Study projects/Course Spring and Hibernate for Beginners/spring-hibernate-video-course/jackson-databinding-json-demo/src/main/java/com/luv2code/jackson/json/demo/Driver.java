package com.luv2code.jackson.json.demo;

import java.io.File;
import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			// create object mapper
			
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and map/convert to java POJO
			// data/sample-lite.json
			
			Student theStudent = 
					mapper.readValue(new File("data/sample-lite.json"),Student.class);
			
			// print first name and last name
			
			System.out.println("first name = " + theStudent.getFirstName());
			System.out.println("last name = " + theStudent.getLastName());

	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		try {
			// create object mapper
			
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and map/convert to java POJO
			// data/sample-full.json
			
			Student theStudent = 
					mapper.readValue(new File("data/sample-full.json"),Student.class);
			
			// print first name and last name
			
			System.out.println("first name = " + theStudent.getFirstName());
			System.out.println("last name = " + theStudent.getLastName());

			// print out address: street and city
			Address temAddress = theStudent.getAddress();
			System.out.println("Street = " + temAddress.getStreet());
			System.out.println("City = " + temAddress.getCity());
			
			// print out languages
			
			for(String tempLang : theStudent.getLanguages()) {
				System.out.println(tempLang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
