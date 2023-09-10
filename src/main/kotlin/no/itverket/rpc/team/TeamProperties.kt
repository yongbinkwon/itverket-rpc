package no.itverket.rpc.team

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "rpc")
class TeamProperties(
    teams: Map<String, String>
) {
    private val teams = teams.map(::TeamProperty)

    fun allTeams() = teams
}

data class TeamProperty(
    val teamName: String,
    val address: String
) {
    constructor(entry: Map.Entry<String, String>): this(entry.key, entry.value)
}