package com.example.demo.aop.aspects.introductions

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.DeclareParents
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.stereotype.Service


/**
 * @author Ihar Zharykau
 */
class Introductions {
}

interface SampleA{
    fun doSomething() {
        println("MSG")
    }
}

@Service
class SampleAImpl: SampleA{}

interface SampleB{
    fun doB();
}

@Service
class SampleBImpl: SampleB{
    override fun doB() {
        print("dob")
    }
}


@Aspect
@Service
class IntroductionsAspect {
    var logger = LoggerFactory.getLogger(IntroductionsAspect::class.java);

    // TODO: verify, why not working
//    companion object {
//        @DeclareParents(value = "com.example.demo.aop.aspects.introductions.SampleA+", defaultImpl = SampleBImpl::class)
//        @JvmField
//        var shoutable: SampleB? = null
//    }

    @Around("target(com.example.demo.aop.aspects.introductions.SampleA)")
    fun doSomethingWrapper(proceedingJoinPoint: ProceedingJoinPoint): Any?{
        logger.info("IntroductionsAspect#doSomethingWrapper")
        return proceedingJoinPoint.proceed()
    }

}
