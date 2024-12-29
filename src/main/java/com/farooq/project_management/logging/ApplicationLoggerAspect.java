package com.farooq.project_management.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.farooq.project_management.controller..*)")
    public void definePackagePointcuts() {
        // empty method just to name the location specified in the pointcut
    }

    //Before and After Aspect of logging
//    @Before("definePackagePointcuts()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.debug("\n \n \n");
//        log.debug("************** Before Method Execution ************** \n {}.{} () with arguments[s] = {}",
//                joinPoint.getSignature().getDeclaringTypeName(),
//                joinPoint.getSignature().getName(),
//                Arrays.toString(joinPoint.getArgs()));
//        log.debug("______________________________________________________ \n \n \n");
//    }

    //Around aspect covers both Before and After
    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("\n \n \n");
        log.debug("************** Before Method Execution ************** \n {}.{} () with arguments[s] = {}",
                proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(),
                Arrays.toString(proceedingJoinPoint.getArgs()));
        log.debug("______________________________________________________ \n \n \n");

        Object object = proceedingJoinPoint.proceed();

        log.debug("\n \n \n");
        log.debug("************** After Method Execution ************** \n {}.{} () with arguments[s] = {}",
                proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(),
                Arrays.toString(proceedingJoinPoint.getArgs()));
        log.debug("______________________________________________________ \n \n \n");

        return object;
    }
}
