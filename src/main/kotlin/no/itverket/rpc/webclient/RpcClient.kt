package no.itverket.rpc.webclient

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriBuilder

@Service
class RpcClient {
    private val client = WebClient.builder().build()

    suspend fun playCheater(host: String, sign: String) =
        client.getRequest(host = host, path = "rpc/cheater", queryParams = mapOf("sign" to sign))

    private suspend fun WebClient.getRequest(
        scheme: String = "http", host: String, port: Int = 8080, path: String, queryParams: Map<String, String>
    ) = get()
        .uri { builder -> builder.scheme(scheme).host(host).port(port).path(path).queryParams(queryParams).build() }
        .retrieve()
        .awaitBody<String>()

    private fun UriBuilder.queryParams(params: Map<String, String>) = apply {
        params.entries.forEach { queryParam(it.key, it.value) }
    }
}