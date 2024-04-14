package com.example.convert.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcuts {
    @Pointcut("execution(* com.example.convert.controller.*.*(..))")
    public void allMethodsFromControllers(){
    }

    @Pointcut("execution(* com.example.convert.services.*.*(..))")
    public void allMethodsFromServices(){
    }
}
