package no.itverket.rpc.matchmaking

import org.springframework.data.jpa.repository.JpaRepository

interface ScheduledMatchRepository: JpaRepository<ScheduledMatch, Long> {

}