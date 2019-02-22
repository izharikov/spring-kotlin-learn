package com.example.demo.services

import com.example.demo.models.Comment
import com.example.demo.models.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.toMono
import java.time.Duration

/**
 * @author Ihar Zharykau
 */


interface PostsService {
    fun getAllPosts(): Flux<Post>
    fun getAllPostsWithComments(offset: Int, limit: Int): Flux<Post>
    fun getAllComments(): Flux<Comment>
    fun getCommentsByPostId(postId: String): Flux<Comment>;
}

@Service
class PostServiceImpl @Autowired constructor(val postServiceConfiguration: PostServiceConfiguration) : PostsService {
    override fun getCommentsByPostId(postId: String): Flux<Comment> {
        return client().get()
                .uri { uriBuilder -> uriBuilder.path(postServiceConfiguration.comments).queryParam("postId", postId).build() }
                .retrieve()
                .bodyToFlux(Comment::class.java)
                .timeout(Duration.ofMillis(postServiceConfiguration.timeout), Flux.error(Exception("Timeout exception")))
    }

    override fun getAllPostsWithComments(offset: Int, limit: Int): Flux<Post> {
        return getAllPosts()
                .skip(offset.toLong())
                .take(limit.toLong())
                .map { it ->
                    getCommentsByPostId(it.id!!).collectList().zipWith(it.toMono())
                }.flatMap { it -> it }
                .map { it -> it.t2.comments = it.t1; it.t2 }

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

@ConfigurationProperties(prefix = "posts.service")
@EnableConfigurationProperties(PostServiceConfiguration::class)
@Service
class PostServiceConfiguration {
    lateinit var url: String;
    lateinit var posts: String;
    lateinit var comments: String;
    var timeout: Long = 0;
}