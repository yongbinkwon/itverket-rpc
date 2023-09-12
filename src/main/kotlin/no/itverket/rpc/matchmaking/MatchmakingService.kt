package no.itverket.rpc.matchmaking

import no.itverket.rpc.sign.Sign
import no.itverket.rpc.statistics.MatchStatisticRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchmakingService(
    private val queuedPlayerRepository: QueuedPlayerRepository,
    private val matchStatisticRepository: MatchStatisticRepository
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
        val opponent = playerToMatch findMatch queuePool
        val match = playerToMatch versus opponent
        val result = match.startMatch()
        matchStatisticRepository.save(result)
        println(result)
        matchAllPlayers(remainingPlayers.removePlayerFromPool(opponent))
    }

    private fun List<QueuedPlayer>.removePlayerFromPool(player: QueuedPlayer?) = player?.let { this - it } ?: this
}