//LoggingAspect.java
package com.thinkproject.rest_project.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Before("within(com.thinkproject.rest_project.service..*)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering {} with arguments {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "within(com.thinkproject.rest_project.service..*)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exiting {} with result {}", joinPoint.getSignature(), result);
    }
    
    @AfterThrowing(pointcut = "within(com.thinkproject.rest_project.service..*)", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in {}: {}", joinPoint.getSignature(), ex.getMessage());
    }
}

