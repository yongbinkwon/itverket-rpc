package no.itverket.rpc.webclient

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class RpcClient {
    private val client = WebClient.builder().build()

    suspend fun playCheater(host: String, sign: String) =
        client.get()
            .uri { builder -> builder.host(host).port(8080).path("rpc").queryParam("sign", sign).build() }
            .retrieve()
            .awaitBody<String>()
}