package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class MyDemoLoggingAspect {
	
	// this is where we add all of our related advices for logging 
	
	//let's start with an @Before advice

	// @Before("execution(public void add*())")    //  pointcut expression

	
    // @Before("execution(* add*(Account))")                            // wrong way
    // @Before("execution(* add*(com.luv2code.aopdemo.Account))")       // right way for one parameter (the full name of class we created the object from her)
    // @Before("execution(* add*(com.luv2code.aopdemo.Account,..))")    // first parameter should be a Account and any parameter
   	// @Before("execution(* add*(..))")                                 // with any number or type of parameter and method name start whit add

	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")            // with object created from com.luv2code.aopdemo.dao package class
	public void beforeAddAccountAdvice() {
		
		// our customer code
		
		System.out.println("\n======>>>Executing @Before advice on addAccount");
		
	}
	

}

































