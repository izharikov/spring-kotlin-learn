package com.example.demo.services

import com.example.demo.models.SomeModel
import org.springframework.context.annotation.Primary

interface Service {
    fun getModel(): SomeModel;
}

@org.springframework.stereotype.Service
class ServiceImplementation : Service {
    override fun getModel(): SomeModel {
        return SomeModel("123", 12);
    }

}

@org.springframework.stereotype.Service
@Primary
class AnotherImplementationChanged : Service {
    override fun getModel(): SomeModel {
        return SomeModel("123", 2);
    }

}