package io.starter.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class TimeTraceAop {

    @Around("execution(* io.starter..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("Start : " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();

            long duration = finish - start;

            System.out.println("End : " + joinPoint + " , " + duration + "ms");
        }
    }
}
