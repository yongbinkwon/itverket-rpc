package no.itverket.rpc.match

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.itverket.rpc.sign.Paper
import no.itverket.rpc.sign.Rock
import no.itverket.rpc.sign.Scissor
import no.itverket.rpc.sign.Sign
import no.itverket.rpc.team.TeamProperties
import no.itverket.rpc.team.TeamProperty
import no.itverket.rpc.webclient.RpcClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MatchService(
    private val rpcClient: RpcClient,
    private val teamProperties: TeamProperties
) {
    @Scheduled(fixedDelay = 20 * 1000, initialDelay = 10 * 1000)
    fun playAllCheaters() = runBlocking {
        teamProperties.allTeams().forEach {
            launch { playVersusCheater(it) }
        }
    }

    private suspend fun playVersusCheater(team: TeamProperty) {
        val sign = Sign.random()
        val opponentSign = rpcClient.playCheater(team, sign.toString())
        opponentSign?.run {
            val match = Player("Binneling", sign) versus Player(team.teamName, Sign.fromString(this))
            println(match.startMatch())
        }
    }

}