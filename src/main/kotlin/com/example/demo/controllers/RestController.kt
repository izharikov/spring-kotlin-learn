package com.example.demo.controllers

import com.example.demo.beans.configuration.EnvSpecificConfiguration
import com.example.demo.beans.request.RequestScopeBean
import com.example.demo.models.InputModel
import com.example.demo.models.SomeModel
import com.example.demo.services.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/rest")
class RestController @Autowired constructor(private val service: Service) {

    @Autowired
    @Qualifier("serviceImplementation")
    lateinit var anotherService: Service;

    @Autowired
    lateinit var config: EnvSpecificConfiguration;

    @GetMapping("/model")
    fun model(): SomeModel {
        return service.getModel()
    }

    @GetMapping("/env")
    fun config(): Map<String, String> {
        return mapOf("env" to config.getValue())
    }

    @GetMapping("/another-model")
    fun anotherModel(): SomeModel {
        return anotherService.getModel()
    }

    @GetMapping("/index")
    fun index(): Map<String, String> {
        return mapOf("1" to "2")
    }

    @PostMapping("/validate")
    fun validate(@Valid @RequestBody inputModel: InputModel): InputModel {
        return inputModel
    }
}