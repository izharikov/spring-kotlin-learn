package com.example.demo

import com.example.demo.internal.CustomBeanNameGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args){
        this.setBeanNameGenerator(CustomBeanNameGenerator())
    }
}

