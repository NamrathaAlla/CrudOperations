package com.mss.user;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class UserServiceAspect {

	@Before(value = "execution(* com.mss.user.UserService.*(..))")
	public void startMethod(JoinPoint joinPoint)
	{
		
		log.info("\n======> Started with method "+joinPoint.getSignature().getName());
	}
	
	@After(value = "execution(* com.mss.user.UserService.*(..))")
	public void endMethod(JoinPoint joinPoint)
	{
		

		log.info("\n======> Completed execution of method "+joinPoint.getSignature().getName());
	}
	
}
