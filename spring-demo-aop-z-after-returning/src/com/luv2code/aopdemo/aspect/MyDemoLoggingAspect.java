package com.luv2code.aopdemo.aspect;


import java.util.List;

// import org.aopalliance.intercept.Joinpoint;           wrong import

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
	
	
	
	private void connvertAccountNameToUpperCase(List<Account> result) {
		 // loop through accounts
		for(Account temAccount : result ) {
		// get uppercase version of name
		 String theUpperName = temAccount.getName().toUpperCase();
		// update the name on the account
		 temAccount.setName(theUpperName);
		}
	}
	
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut =  "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",
			returning = "result"
			)
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


































	