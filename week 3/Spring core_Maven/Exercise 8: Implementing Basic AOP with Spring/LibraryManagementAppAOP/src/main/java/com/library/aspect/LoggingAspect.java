package com.library.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.BookService.*(..))")
    public void beforeAdvice() {
        System.out.println("[AOP] Before method execution in BookService.");
    }

    @After("execution(* com.library.BookService.*(..))")
    public void afterAdvice() {
        System.out.println("[AOP] After method execution in BookService.");
    }
}