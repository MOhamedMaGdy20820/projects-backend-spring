package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class SutdentController {
	
	@RequestMapping("/showForm")
	public String ShowForm(Model theModel) {
		// create a new student object
		Student theStudent = new Student();
		// add student object to the Model
		theModel.addAttribute("student",theStudent);
		
		return "student-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student theStudent) {
		// log the input data
		System.out.println("thestudent "+ theStudent.getFirstName()
		 						    +" "+theStudent.getLastName());
		return "student-confirmation";
		
	}

	
	
	
	
}
