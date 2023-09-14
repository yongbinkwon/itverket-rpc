package no.itverket.rpc.matchmaking

import no.itverket.rpc.sign.Sign
import no.itverket.rpc.sign.exception.UnknownSignNameException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("queueGame")
class MatchmakingController(
    private val matchmakingService: MatchmakingService
) {
    @GetMapping
    fun queuePlayer(teamName: String, sign: String) {
        println("$teamName has queued to a match")
        val validatedSign = try {Sign.fromString(sign)} catch (e: UnknownSignNameException) {
            println("$teamName error: ${e.message}")
            return
        }
        matchmakingService.queuePlayer(teamName, validatedSign)
    }
}