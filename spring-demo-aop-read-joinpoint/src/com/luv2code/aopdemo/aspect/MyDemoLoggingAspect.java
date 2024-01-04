package com.luv2code.aopdemo.aspect;


// import org.aopalliance.intercept.Joinpoint;           wrong import

import org.aspectj.lang.JoinPoint;
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


































	