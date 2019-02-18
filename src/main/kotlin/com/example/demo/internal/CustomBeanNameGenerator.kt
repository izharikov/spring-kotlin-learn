package com.example.demo.internal

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Service
class CustomBeanNameGenerator : AnnotationBeanNameGenerator() {
    var logger = LoggerFactory.getLogger(CustomBeanNameGenerator::class.java)


    override fun generateBeanName(definition: BeanDefinition, registry: BeanDefinitionRegistry): String {
        logger.info("Generate bean name for ${definition.beanClassName}")
        return super.generateBeanName(definition, registry)
    }
}
