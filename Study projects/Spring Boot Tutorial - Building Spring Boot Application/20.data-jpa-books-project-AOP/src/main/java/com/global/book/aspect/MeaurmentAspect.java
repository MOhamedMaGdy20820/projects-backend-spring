package com.global.book.aspect;


import org.apache.commons.lang3.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

@Aspect
@Order(0)
@Component
public class MeaurmentAspect {
	
	
	Logger log = LoggerFactory.getLogger(MeaurmentAspect.class);
	
	@Around(value = "execution(* com.global.book.service..*(..))")
	public Object logTime(ProceedingJoinPoint  joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("KPI:");
		sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
				.append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs(), ",")).append(")");
		sb.append("\ttook: ");
		Object returnValue = joinPoint.proceed();
		log.info(sb.append(System.currentTimeMillis() - startTime).append(" ms.").toString());
		
		return returnValue;
	}

	
}
