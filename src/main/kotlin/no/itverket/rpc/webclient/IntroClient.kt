package no.itverket.rpc.webclient

import no.itverket.rpc.team.TeamProperty
import org.springframework.stereotype.Service

@Service
class IntroClient: BaseWebClient() {
    suspend fun theBest(team: TeamProperty) = getRequest(team = team, path = "/best")
    suspend fun theWorst(team: TeamProperty) = getRequest(team = team, path = "/worst")
}