package no.itverket.rpc.statistics

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "MatchStatistic")
class MatchStatistic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false)
    private val player1: String,

    @Column(nullable = false)
    private val player2: String,

    @Column
    private val winner: String? = null,

    @Column
    private val loser: String? = null,

    @Column(nullable = false)
    private val matchDate: LocalDateTime = LocalDateTime.now()
) {
    override fun equals(other: Any?) = this === other || other is MatchStatistic && this.equals(other)
    private fun equals(other: MatchStatistic) =
        id == other.id &&
                player1 == other.player1 &&
                player2 == other.player2 &&
                winner == other.winner &&
                loser == other.loser &&
                matchDate == other.matchDate

    override fun hashCode() = Objects.hash(id, player1, player2, winner, loser, matchDate)
}