package com.example.demo.services.impl

import com.example.demo.http.clients.apache.BaseApacheHttpClient
import com.example.demo.internal.CustomBeanNameGenerator
import com.example.demo.models.Comment
import com.example.demo.models.Post
import com.example.demo.services.PostServiceConfiguration
import com.example.demo.services.PostsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import com.fasterxml.jackson.module.kotlin.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.annotation.PreDestroy

@Service
class PostServiceApacheHttpClient
@Autowired constructor(val postServiceConfiguration: PostServiceConfiguration)
    : BaseApacheHttpClient(), PostsService {

    var logger = LoggerFactory.getLogger(PostServiceApacheHttpClient::class.java)

    override fun getAllPosts(): Flux<Post> {
        return getFlux(postServiceConfiguration.url + postServiceConfiguration.posts) {
            objectMapper.readValue<List<Post>>(it)
        }
    }

    override fun getAllComments(): Flux<Comment> {
        return getFlux(postServiceConfiguration.url + postServiceConfiguration.comments) {
            objectMapper.readValue<List<Comment>>(it)
        }
    }

    override fun getCommentsByPostId(postId: String): Flux<Comment> {
        return getFlux("${postServiceConfiguration.url}${postServiceConfiguration.posts}?postId=$postId") {
            objectMapper.readValue<List<Comment>>(it)
        }
    }

    @PreDestroy
    override fun closeHttpClient(){
        logger.info("Closing HTTP client")
        super.closeHttpClient();
    }
}