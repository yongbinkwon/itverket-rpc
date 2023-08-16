package no.itverket.rpc.match

import no.itverket.rpc.sign.Paper
import no.itverket.rpc.sign.Rock
import no.itverket.rpc.sign.Scissor
import no.itverket.rpc.sign.Sign
import no.itverket.rpc.team.TeamProperties
import no.itverket.rpc.team.TeamProperty
import no.itverket.rpc.webclient.RpcClient
import org.springframework.stereotype.Service

@Service
class MatchService(
    private val rpcClient: RpcClient,
    private val teamProperties: TeamProperties
) {
    companion object {
        private val SIGNS = listOf(Rock(), Paper(), Scissor())
    }

    private suspend fun playVersusCheater(team: TeamProperty) {
        val sign = SIGNS.random()
        val opponentSign = rpcClient.playCheater(team.address, sign.toString())
        val match = Player("Binneling", sign) versus Player(team.teamName, Sign.fromString(opponentSign))
        println(match.startMatch())
    }

}