package com.example.demo.models

import reactor.core.publisher.Flux

/**
 * @author Ihar Zharykau
 */

data class Post(val userId: String?, val id: String?, val title: String?, val body: String?, var comments: List<Comment>? = listOf())

data class Comment(val postId: String?, val id: String?, val name: String?, val email: String?, val body: String?)

data class Greeting(val msg: String?)