package com.example.demo.services.impl

import com.example.demo.models.Comment
import com.example.demo.models.Post
import com.example.demo.services.PostServiceConfiguration
import com.example.demo.services.PostsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.time.Duration

@Service
@Primary
class PostServiceImplWebClient @Autowired constructor(val postServiceConfiguration: PostServiceConfiguration) : PostsService {
    override fun getCommentsByPostId(postId: String): Flux<Comment> {
        return client().get()
                .uri { uriBuilder -> uriBuilder.path(postServiceConfiguration.comments).queryParam("postId", postId).build() }
                .retrieve()
                .bodyToFlux(Comment::class.java)
                .timeout(Duration.ofMillis(postServiceConfiguration.timeout), Flux.error(Exception("Timeout exception")))
    }

    override fun getAllComments(): Flux<Comment> {
        return client().get()
                .uri(postServiceConfiguration.comments)
                .retrieve().bodyToFlux(Comment::class.java)
                .timeout(Duration.ofMillis(postServiceConfiguration.timeout), Flux.error(Exception("Timeout exception")))
    }

    override fun getAllPosts(): Flux<Post> {
        return client().get()
                .uri(postServiceConfiguration.posts)
                .retrieve().bodyToFlux(Post::class.java)
                .timeout(Duration.ofMillis(postServiceConfiguration.timeout), Flux.error(Exception("Timeout exception")))
    }

    private fun client(): WebClient {
        return WebClient.builder()
                .baseUrl(postServiceConfiguration.url)
                .build()
    }
}
