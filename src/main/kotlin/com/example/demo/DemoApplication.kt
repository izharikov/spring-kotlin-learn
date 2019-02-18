package com.example.demo

import com.example.demo.internal.CustomBeanNameGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass=true)
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args){
        this.setBeanNameGenerator(CustomBeanNameGenerator())
    }
}

