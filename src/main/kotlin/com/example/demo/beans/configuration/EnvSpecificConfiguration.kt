package com.example.demo.beans.configuration

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

interface EnvSpecificConfiguration{
    fun getValue(): String;
}

@Component
@Profile("dev")
class DevEnvSpecificConfiguration : EnvSpecificConfiguration{
    override fun getValue(): String {
        return "DEV_MODE";
    }

}

@Component
@Profile("prod")
class ProdEnvSpecificConfiguration : EnvSpecificConfiguration{
    override fun getValue(): String {
        return "PROD_MODE"
    }

}