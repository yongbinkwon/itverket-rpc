package no.itverket.rpc.webclient

import no.itverket.rpc.team.TeamProperty
import org.springframework.stereotype.Service

@Service
class RpcClient: BaseWebClient() {
    suspend fun playCheater(team: TeamProperty, sign: String) =
        getRequest(team = team, path = "rps/cheater", queryParams = mapOf("sign" to sign))
}