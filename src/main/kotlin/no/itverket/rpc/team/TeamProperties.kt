package no.itverket.rpc.team

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "teams")
class TeamProperties(
    private val properties: List<TeamProperty>
) {
    fun allTeams() = properties
}

data class TeamProperty(
    val teamName: String,
    val address: String
)