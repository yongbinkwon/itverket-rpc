package no.itverket.rpc.webclient

import no.itverket.rpc.team.TeamProperty
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientRequestException
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriBuilder
import reactor.netty.http.client.HttpClient

abstract class BaseWebClient {
    val httpClient = HttpClient.create()
        .responseTimeout(java.time.Duration.ofSeconds(2)) // Set the read timeout to 10 seconds

    // Configure the WebClient with the custom HttpClient
    val connector = ReactorClientHttpConnector(httpClient)
    private val client = WebClient.builder().clientConnector(connector).build()

    protected suspend fun getRequest(
        scheme: String = "http",
        team: TeamProperty,
        port: Int = 8080,
        path: String,
        queryParams: Map<String, String> = mapOf()
    ) = try {
        client.get()
            .uri { builder ->
                builder.scheme(scheme).host(team.address).port(port).path(path).queryParams(queryParams).build()
            }
            .retrieve()
            .awaitBody<String>()
    } catch (e: Exception) {
        when (e) {
            is WebClientRequestException, is WebClientResponseException.NotFound -> {
                println("Every second that ${team.teamName} does not create the endpoint $path, a kitten loses its mother")
                null
            }
            else -> throw e
        }

    }

    private fun UriBuilder.queryParams(params: Map<String, String>) = apply {
        params.entries.forEach { queryParam(it.key, it.value) }
    }
}