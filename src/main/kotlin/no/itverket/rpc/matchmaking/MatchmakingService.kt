package no.itverket.rpc.matchmaking

import no.itverket.rpc.sign.Sign
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchmakingService(
    private val queuedPlayerRepository: QueuedPlayerRepository
) {
    @Transactional
    fun queuePlayer(team: String, sign: Sign) {
        val player = QueuedPlayer(sign = sign.toString(), team = team)
        queuedPlayerRepository.save(player)
    }

    @Transactional
    @Scheduled(fixedDelay = 10 * 1000)
    fun playMatches() {
        val queuePool = queuedPlayerRepository.findByProcessedFalse()
        matchAllPlayers(queuePool)
    }

    fun matchAllPlayers(queuePool: List<QueuedPlayer>) {
        val playerToMatch = queuePool.firstOrNull() ?: return
        val remainingPlayers = queuePool.drop(1)
        matchPlayer(playerToMatch, remainingPlayers)
        matchAllPlayers(remainingPlayers)
    }

    private fun matchPlayer(player: QueuedPlayer, queuePool: List<QueuedPlayer>) {
        val match = player findMatch queuePool
        println(match.startMatch())
    }
}