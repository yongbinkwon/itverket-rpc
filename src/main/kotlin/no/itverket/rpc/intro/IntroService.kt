package no.itverket.rpc.intro

import kotlinx.coroutines.*
import no.itverket.rpc.team.TeamProperties
import no.itverket.rpc.team.TeamProperty
import no.itverket.rpc.webclient.IntroClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class IntroService(
    private val introClient: IntroClient,
    private val teamProperties: TeamProperties
) {

    @Scheduled(fixedDelay = 20 * 1000)
    fun theWorstAndTheBest() = runBlocking {
        teamProperties.allTeams().forEach {
            println(it.teamName)
            launch { theBest(it) }
            launch { theWorst(it) }
        }
    }

    private suspend fun theBest(team: TeamProperty) {
        val response = introClient.theBest(team)
        response?.run {
            if (this.removeWhitespace().lowercase() == "bin") println("${team.teamName} proclaims that I am the best")
            else println("What's worse; believing the earth is flat or ${team.teamName} believing $this is the best")
        }
    }

    private suspend fun theWorst(team: TeamProperty) {
        val response = introClient.theWorst(team)
        response?.run {
            if (this.removeWhitespace().lowercase() == "tomhenrik") println("It takes a grade A hater to call your own boss the worst ${team.teamName}, but I can't really disagree")
            else println("personally I could think of exactly one other person than $this that is worse, but you do you ${team.teamName}")
        }
    }

    fun String.removeWhitespace() = replace(Regex("\\s+"), "")
}