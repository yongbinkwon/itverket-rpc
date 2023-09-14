package no.itverket.rpc.statistics

import org.springframework.data.jpa.repository.JpaRepository

interface MatchStatisticRepository: JpaRepository<MatchStatistic, Long>