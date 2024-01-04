package com.luv2code.aopdemo.aspect;


import java.util.List;
import java.util.logging.Logger;

// import org.aopalliance.intercept.Joinpoint;           wrong import

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.TargetClassAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.AroundWithLoggerDemoApp;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private  Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFourtune(..))")
	private Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @Around on method: " + method);
		
		// get begin timestamp 
		long begin = System.currentTimeMillis();
		myLogger.info("\n1. before getFourtune");
		// now ,let's execute the method  --->  call the method and story the result in the object
		Object result = theProceedingJoinPoint.proceed();
		myLogger.info("3. after getFourtune");

		
		// get end timestamp
		long end = System.currentTimeMillis();	
		
		// compute duration and display it
		long duration = end - begin ;
		myLogger.info("\n====> Duration: " + duration / 1000.0+ "seconds");
		
		return result;

	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @After (Finally) on method: " 
										+ method);
		
	}
	
	@AfterThrowing(
			pointcut =  "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing =  "theExc" 	)
	public void afterThrowingFindAccountsAdvice( 
					 JoinPoint theJoinPoint ,Throwable theExc) {
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + method);
		
		//log the exception
		myLogger.info("\n=====>>> the exception is: " + theExc);

	}
	
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut =  "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result"		)
	public void afterReturningFindAccountsAdvice(
			         JoinPoint theJoinPoint,List<Account> result) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		myLogger.info("\n=====>>> in advice result is: " + result);
		
		// let's post-process the data .. let's modify it :-)
		
		// convert the account names to uppercase
		connvertAccountNameToUpperCase(result);
		
		myLogger.info("\n=====>>> in advice result is: " + result);	
	}
	
	private void connvertAccountNameToUpperCase(List<Account> result) {
		 // loop through accounts
		for(Account temAccount : result ) {
		// get uppercase version of name
		 String theUpperName = temAccount.getName().toUpperCase();
		// update the name on the account
		 temAccount.setName(theUpperName);
		}
	}
	

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")                                                          
	public void beforeAddAccountAdvice(JoinPoint theJoinpoint) {
		// advice
		myLogger.info("\n======>>> Executing @Before advice on addAccount");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinpoint.getSignature();
		myLogger.info("Method: "+methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = theJoinpoint.getArgs();
		
		// loop thru args
		for(Object temArg : args) {
			myLogger.info(temArg.toString());
			
		if (temArg instanceof Account) {
				// downcast and print Account specific stuff
				Account myAccount = (Account) temArg;
				myLogger.info("account name: " + myAccount.getName());
				myLogger.info("account level: " +  myAccount.getLevel());
			}
		}
		
	}
}


































	