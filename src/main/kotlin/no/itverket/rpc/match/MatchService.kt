package no.itverket.rpc.match

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    @Scheduled(fixedDelay = 10*1000)
    fun playAllCheaters() = runBlocking {
        teamProperties.allTeams().forEach { launch { playVersusCheater(it) } }
    }

    private suspend fun playVersusCheater(team: TeamProperty) {
        val sign = SIGNS.random()
        val opponentSign = rpcClient.playCheater(team.address, sign.toString())
        val match = Player("Binneling", sign) versus Player(team.teamName, Sign.fromString(opponentSign))
        println(match.startMatch())
    }

}

/*
suspend fun hei(string: String) {
    delay(5000)
    println(string)
}

@OptIn(ExperimentalTime::class)
fun main() {
    println(measureTime { runBlocking {
        listOf("hei", "du").map { async { hei(it) } }
    } })
}

 */