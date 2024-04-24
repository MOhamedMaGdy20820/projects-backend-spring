package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstrainValidator 
		implements ConstraintValidator<CourseCode, String> {

	
	private String coursePrifix ;
	
	@Override
	public void initialize(CourseCode theCourseCode) { // theCourseCode object form CourseCode interface
		// TODO Auto-generated method stub
		// ConstraintValidator.super.initialize(constraintAnnotation);
		
		coursePrifix = theCourseCode.value(); // => LUV
	}
	

	@Override                               // ymkn wad3 error message edafeya hena
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		//                         ^ enter by user
		
		// TODO Auto-generated method stub

		boolean result;
		
		if(theCode != null) {
			result = theCode.startsWith(coursePrifix);
		}else {
			result = false;
		}
		return result;
	}
	
	

}






















