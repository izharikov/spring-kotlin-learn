package com.example.demo.http.clients.apache

import com.fasterxml.jackson.module.kotlin.*
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.concurrent.FutureCallback
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient
import org.apache.http.impl.nio.client.HttpAsyncClients
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.InputStream
import java.lang.Exception
import java.util.concurrent.CompletableFuture

abstract class BaseApacheHttpClient {
    protected val objectMapper = jacksonObjectMapper();
    protected val client: CloseableHttpAsyncClient = HttpAsyncClients.createDefault();

    protected fun <T> getFlux(url: String, jsonToListMapper: (inputStream: InputStream) -> List<T>): Flux<T> {
        client.start()
        val result = invokeClient(client, HttpGet(url))
        return Mono
                .fromFuture(result)
                .map { jsonToListMapper(it.entity.content) }
                .flatMapMany { Flux.fromIterable(it) }
    }

    private fun invokeClient(client: CloseableHttpAsyncClient, request: HttpUriRequest): CompletableFuture<HttpResponse> {
        val futureResponse = CompletableFuture<HttpResponse>();
        client.execute(request, object : FutureCallback<HttpResponse> {
            override fun cancelled() {
                futureResponse.cancel(false)
            }

            override fun completed(result: HttpResponse?) {
                futureResponse.complete(result)
            }

            override fun failed(ex: Exception?) {
                futureResponse.completeExceptionally(ex)
            }

        })
        return futureResponse
    }

    protected open fun closeHttpClient(){
        client.close()
    }
}