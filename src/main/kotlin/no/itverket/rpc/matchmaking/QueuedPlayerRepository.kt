package no.itverket.rpc.matchmaking

import org.springframework.data.jpa.repository.JpaRepository

interface QueuedPlayerRepository: JpaRepository<QueuedPlayer, Long> {
    fun findByProcessedFalse(): List<QueuedPlayer>
}