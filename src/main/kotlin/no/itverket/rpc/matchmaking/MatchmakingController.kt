package no.itverket.rpc.matchmaking

import no.itverket.rpc.sign.Sign
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("schedule")
class MatchmakingController(
    private val matchmakingService: MatchmakingService
) {
    @GetMapping
    fun queuePlayer(teamName: String, sign: String) {
        matchmakingService.queuePlayer(teamName, Sign.fromString(sign))
    }
}