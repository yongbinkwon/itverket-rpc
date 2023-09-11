package no.itverket.rpc.matchmaking

import no.itverket.rpc.sign.Sign
import org.springframework.stereotype.Service

@Service
class MatchmakingService(
    private val scheduledMatchRepository: ScheduledMatchRepository
) {
    fun scheduleMatch(team: String, sign: Sign) {
        val match = ScheduledMatch(sign = sign.toString(), team = team)
        scheduledMatchRepository.save(match)
    }
}