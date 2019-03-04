package com.example.demo.endpoints

import com.example.demo.services.PostsService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.*


@Configuration
class PostsEndpointConfiguration {
    @Bean
    fun webClientRoutes(postsService: PostsService) =  postsServiceRouter("wcl", postsService)

    @Bean
    fun apacheClientRoutes(@Qualifier("postServiceApacheHttpClient") postsService: PostsService)
            = postsServiceRouter("ap", postsService)

    fun postsServiceRouter(prefix: String, postsService: PostsService) = router {
        GET("/$prefix/posts") { req ->
            ServerResponse.ok().body(
                    if (req.queryParam("includeAll").isPresent)
                        postsService.getAllPostsWithComments(0, 10)
                    else postsService.getAllPosts())
        }
        GET("/$prefix/comments") { ServerResponse.ok().body(postsService.getAllComments()) }
    }


    @Bean
    fun route() = router {
        GET("/route") { _ -> ServerResponse.ok().body(fromObject(arrayOf(1, 2, 3))) }
    }

}