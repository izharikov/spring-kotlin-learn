package com.example.demo.aop.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @author Ihar Zharykau
 */
//
@Aspect
@Service
class CustomAspect {
    var logger = LoggerFactory.getLogger(CustomAspect::class.java);

    @Pointcut("within(com.example.demo.controllers..*)")
    private fun aroundControllerPointcut() {
    }

    @Around("aroundControllerPointcut()")// the pointcut expression
    fun wrapControllerCall(proceedingJoinPoint: ProceedingJoinPoint): Any {
        logger.info("CustomAspect#wrapControllerCall")
        return proceedingJoinPoint.proceed()
    }// the pointcut signature

    @Around("within(com.example.demo.services.Service+) && execution(String *(..)) && @annotation(com.example.demo.aop.aspects.DoubleValue)")
    fun doubleValue(proceedingJoinPoint: ProceedingJoinPoint): String {
        val value = proceedingJoinPoint.proceed(proceedingJoinPoint.args) as String
        return value + value;
    }
}

annotation class DoubleValue {}