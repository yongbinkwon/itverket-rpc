package no.itverket.rpc.statistics

import jakarta.persistence.*
import no.itverket.rpc.match.Player
import java.time.LocalDateTime

@Entity
@Table(name = "MatchStatistic")
class MatchStatistic private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column
    private val winner: String? = null,

    @Column
    private val loser: String? = null,

    @Column(nullable = false)
    private val matchDate: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun tied() = MatchStatistic()
        fun results(winner: Player, loser: Player) = MatchStatistic(winner = winner.toString(), loser = loser.toString())
    }

    private fun tied() = (winner == null && loser == null)

    override fun toString() = if (tied()) "tied game" else "$winner beat $loser"

    override fun equals(other: Any?) = this === other || other is MatchStatistic && this.equals(other)
    private fun equals(other: MatchStatistic) = winner == other.winner && loser == other.loser && matchDate == other.matchDate

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (winner?.hashCode() ?: 0)
        result = 31 * result + (loser?.hashCode() ?: 0)
        result = 31 * result + matchDate.hashCode()
        return result
    }
}