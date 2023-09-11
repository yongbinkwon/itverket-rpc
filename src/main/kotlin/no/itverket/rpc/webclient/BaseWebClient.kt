package no.itverket.rpc.webclient

import no.itverket.rpc.team.TeamProperties
import no.itverket.rpc.team.TeamProperty
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientRequestException
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriBuilder

abstract class BaseWebClient {
    private val client = WebClient.builder().build()

    protected suspend fun getRequest(
        scheme: String = "http", team: TeamProperty, port: Int = 8080, path: String, queryParams: Map<String, String> = mapOf()
    ) = try {
        client.get()
            .uri { builder -> builder.scheme(scheme).host(team.address).port(port).path(path).queryParams(queryParams).build() }
            .retrieve()
            .awaitBody<String>()
    } catch (e: WebClientRequestException) {
        println("Every second that ${team.teamName} does not create the endpoint $path, a kitten loses its mother")
        null
    }

    private fun UriBuilder.queryParams(params: Map<String, String>) = apply {
        params.entries.forEach { queryParam(it.key, it.value) }
    }
}