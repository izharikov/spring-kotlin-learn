package com.example.demo.beans.request

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import javax.annotation.PreDestroy

@Component
@RequestScope()
class RequestScopeBean{
    var logger = LoggerFactory.getLogger(RequestScopeBean::class.java)

    fun print(){
        logger.info("$this is used")
    }

    @PreDestroy
    fun destroy(){
        logger.info("$this is destroyed")
    }
}