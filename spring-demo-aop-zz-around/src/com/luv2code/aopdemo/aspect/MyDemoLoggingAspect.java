package com.luv2code.aopdemo.aspect;


import java.util.List;

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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFourtune(..))")
	private Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @Around on method: " + method);
		
		// get begin timestamp 
		long begin = System.currentTimeMillis();
		System.out.println("\n1. before getFourtune");
		// now ,let's execute the method  --->  call the method and story the result in the object
		Object result = theProceedingJoinPoint.proceed();
		System.out.println("3. after getFourtune");

		
		// get end timestamp
		long end = System.currentTimeMillis();	
		
		// compute duration and display it
		long duration = end - begin ;
		System.out.println("\n====> Duration: " + duration / 1000.0+ "seconds");
		
		return result;

	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @After (Finally) on method: " 
										+ method);
		
	}
	
	@AfterThrowing(
			pointcut =  "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing =  "theExc" 	)
	public void afterThrowingFindAccountsAdvice( 
					 JoinPoint theJoinPoint ,Throwable theExc) {
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
		
		//log the exception
		System.out.println("\n=====>>> the exception is: " + theExc);

	}
	
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut =  "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result"		)
	public void afterReturningFindAccountsAdvice(
			         JoinPoint theJoinPoint,List<Account> result) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		System.out.println("\n=====>>> in advice result is: " + result);
		
		// let's post-process the data .. let's modify it :-)
		
		// convert the account names to uppercase
		connvertAccountNameToUpperCase(result);
		
		System.out.println("\n=====>>> in advice result is: " + result);	
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
		System.out.println("\n======>>> Executing @Before advice on addAccount");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinpoint.getSignature();
		System.out.println("Method: "+methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = theJoinpoint.getArgs();
		
		// loop thru args
		for(Object temArg : args) {
			System.out.println(temArg);
			
		if (temArg instanceof Account) {
				// downcast and print Account specific stuff
				Account myAccount = (Account) temArg;
				System.out.println("account name: " + myAccount.getName());
				System.out.println("account level: " +  myAccount.getLevel());
			}
		}
		
	}
}


































	