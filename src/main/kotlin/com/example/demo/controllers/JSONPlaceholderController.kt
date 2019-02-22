package com.example.demo.controllers

import com.example.demo.models.Comment
import com.example.demo.models.Greeting
import com.example.demo.models.Post
import com.example.demo.services.PostsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2
import java.time.Instant
import java.util.stream.Stream
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.bind.annotation.RequestParam
import java.time.Duration


/**
 * @author Ihar Zharykau
 */

@RestController
@RequestMapping("/json")
class JSONPlaceholderController @Autowired constructor(val postsService: PostsService) {


    @GetMapping("/posts")
    fun posts(): Flux<Post> {
        return postsService.getAllPosts()
    }

    @GetMapping("/postsWithComments")
    fun postsWithComments(@RequestParam("offset", defaultValue = "0") offset: Int, @RequestParam("offset", defaultValue = "10") limit: Int = 10): Flux<Post> {
        return postsService.getAllPostsWithComments(offset, limit)
    }

    @GetMapping("/comments")
    fun comments(): Flux<Comment> {
        return postsService.getAllComments()
    }

    @GetMapping("/sse", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    fun events(): Flux<Greeting> {
        val greetingFlux = Flux.fromStream<Greeting>(Stream.generate<Greeting> { Greeting("Hello @" + Instant.now().toString()) })
        val durationFlux = Flux.interval(Duration.ofSeconds(1))
        return Flux.zip(greetingFlux, durationFlux).map { it.t1 }
    }
}