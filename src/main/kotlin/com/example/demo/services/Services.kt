package com.example.demo.services

import com.example.demo.aop.aspects.DoubleValue
import com.example.demo.models.SomeModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource

interface Service {
    fun getModel(): SomeModel;

    @DoubleValue
    fun doSomeWithString(str: String): String{
        return str
    }
}

@ConfigurationProperties(prefix = "service.impl")
@EnableConfigurationProperties(ServiceImplementation::class)
@org.springframework.stereotype.Service
class ServiceImplementation : Service {

//    @Value("\${serviceImpl.name}")
    var name: String? = null;

//    @Value("\${serviceImpl.id}")
    var id: Int? = null;

    override fun getModel(): SomeModel {
        return SomeModel(name ?: "", id ?: -1);
    }

}

@org.springframework.stereotype.Service
@Primary
class AnotherImplementationChanged : Service {
    override fun getModel(): SomeModel {
        return SomeModel("123", 2);
    }

}