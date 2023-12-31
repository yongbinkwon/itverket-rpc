package no.itverket.rpc

import no.itverket.rpc.team.TeamProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(value = [TeamProperties::class])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}