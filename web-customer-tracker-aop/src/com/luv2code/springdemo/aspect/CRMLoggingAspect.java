package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import net.bytebuddy.asm.Advice.Return;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations                  any class |
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
										    	//  any method  |
	private void forControlllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControlllerPackage() || forServicePackage() || forDaoPackage() ")
	private void forAppFlow() {}
	
	// add @Before advice
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		// display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>> in @Before: calling metod: "+ theMethod);
		
		// display the arguments to the method
			// 1. get the arguments 
				Object[] args = theJoinPoint.getArgs();
		
			// 2. loop thru and display arguments 
				for(Object temArg : args) {
					myLogger.info("====> argument: " + temArg);
				}
	}
	
	// add @AfterReturning advice 
	
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "theResult"
			)
	public void AfterReturning(JoinPoint theJoinPoint , Object theResult) {
		// display method we are returning
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>> in @AfterReturning: from metod: "+ theMethod);
		
		// display data returned
		myLogger.info("====>> result: "+ theResult);
		
	}
	
}








