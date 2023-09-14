package no.itverket.rpc.match.result

import no.itverket.rpc.match.Player
import no.itverket.rpc.statistics.MatchStatistic
import java.util.*

abstract class MatchResult(
    private val player1: Player? = null,
    private val player2: Player? = null
) {
    companion object {
        fun tie(player1: Player, player2: Player) = TieResult(player1, player2)
        fun results(winner: Player, loser: Player) = RegularResult(winner, loser)
    }

    abstract fun toStatistic(): MatchStatistic

    override fun equals(other: Any?) = this === other || other is MatchResult && this.sameResult(other)
    protected abstract fun sameResult(other: MatchResult): Boolean
    override fun hashCode() = Objects.hash(player1, player2)
}