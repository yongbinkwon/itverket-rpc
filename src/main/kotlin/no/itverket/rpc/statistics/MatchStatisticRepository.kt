package no.itverket.rpc.statistics

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface MatchStatisticRepository: JpaRepository<MatchStatistic, Long> {
    fun findByMatchDate(matchDate: LocalDateTime): List<MatchStatistic>
    fun countByMatchDate(matchDate: LocalDateTime): Int
}