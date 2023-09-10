package no.itverket.rpc.match

import kotlinx.coroutines.delay
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
import org.springframework.web.reactive.function.client.WebClientRequestException
import java.net.ConnectException
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@Service
class MatchService(
    private val rpcClient: RpcClient,
    private val teamProperties: TeamProperties
) {
    companion object {
        private val SIGNS = listOf(Rock(), Paper(), Scissor())
    }

    @Scheduled(fixedDelay = 10 * 1000)
    fun playAllCheaters() = runBlocking {
        teamProperties.allTeams().forEach {
            launch {
                try {
                    playVersusCheater(it)
                } catch (e: WebClientRequestException) {
                    println("${it.teamName} did not show up to the fight (bwak bwak)")
                }
            }
        }
    }

    private suspend fun playVersusCheater(team: TeamProperty) {
        val sign = SIGNS.random()
        val opponentSign = rpcClient.playCheater(team.address, sign.toString())
        val match = Player("Binneling", sign) versus Player(team.teamName, Sign.fromString(opponentSign))
        println(match.startMatch())
    }

}