package com.global.book.aspect;


import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

@Aspect
@Order(1)
@Component
public class LoggingAspect {
	
	
	Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	
	@Pointcut(value = "execution(* com.global.book.repository.*.*(..))")
	public void forRepositoryLog() {}
	
	@Pointcut(value = "execution(* com.global.book.service.*.*(..))")
	public void forServiceLog () {}
	
	@Pointcut(value = "execution(* com.global.book.controller.*.*(..))")
	public void forControllerLog () {}
	
	@Pointcut(value = "forRepositoryLog() || forServiceLog() || forControllerLog()")
	public void forAllApp() {}
	
	@Before(value = "forAllApp()")
	public void beforMethod(JoinPoint joinPoint) {

		String methodName = joinPoint.getSignature().toShortString();

		log.info("====>  Method Name is >> {}" , methodName );

		Object [] args = joinPoint.getArgs();

		for (Object arg : args) {

			log.info("===> argument >> {}" , arg);
		}

	}

}
