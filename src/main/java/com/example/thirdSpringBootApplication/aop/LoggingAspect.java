package com.example.thirdSpringBootApplication.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("@annotation(Logged)")
	public void before(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		log.info("class is :"+className);
		log.info("entered method " + joinPoint.getSignature().getName());
		
		
	}
	
	@After("@annotation(Logged)")
	public void after(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		log.info("class is :"+className);
		log.info("exited method " + joinPoint.getSignature().getName());
	}

}
