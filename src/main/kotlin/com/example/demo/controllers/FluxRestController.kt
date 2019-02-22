package com.example.demo.controllers

import com.example.demo.models.SomeModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ihar Zharykau
 */

@RestController
@RequestMapping("/flux")
class FluxRestController {

    @GetMapping("/some")
    fun doSomething(): Mono<String> {
        return Mono.just("sample");
    }

    @GetMapping("/sample")
    fun sample(): Flux<SomeModel> {
        return Flux
                .just("1", "2", "3", "4")
                .map { d -> SomeModel(d, d.length) }

    }
}