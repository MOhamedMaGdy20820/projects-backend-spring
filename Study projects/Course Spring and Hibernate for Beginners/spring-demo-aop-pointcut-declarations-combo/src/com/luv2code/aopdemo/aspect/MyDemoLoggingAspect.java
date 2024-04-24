package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class MyDemoLoggingAspect {
	
	// this is where we add all of our related advices for logging 
	
	//let's start with an @Before advice


	// @Before("execution(public void add*())")    //  pointcut expression
    // @Before("execution(* add*(Account))")                            // wrong way
    // @Before("execution(* add*(com.luv2code.aopdemo.Account))")       // right way for one parameter
    // @Before("execution(* add*(com.luv2code.aopdemo.Account,..))")    // first parameter should be a Account and any parameter
   	// @Before("execution(* add*(..))")                                 // with any number or type of parameter and method name start whit add
	// @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")         // with object created from com.luv2code.aopdemo.dao package class

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")      
	private void forDaoPackage() {} 
	
	// create pointcut for getter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")      
	private void getter() {} 
	
	// create pointcut for setter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")      
	private void setter() {} 
	
	// create pointcut: include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")      
	private void forDaoPackageNoGetterSetter() {} 
		
	
	
	@Before("forDaoPackageNoGetterSetter()")                                                          
	public void beforeAddAccountAdvice() {
		// advice
		System.out.println("\n======>>> Executing @Before advice on addAccount");
	}
	
	@Before("forDaoPackageNoGetterSetter()")                     
	public void performApiAnalytics() {
		System.out.println("\n======>>> performing API analytics");
	}
	

}

































