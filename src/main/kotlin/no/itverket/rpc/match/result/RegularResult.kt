package no.itverket.rpc.match.result

import no.itverket.rpc.match.Player
import no.itverket.rpc.statistics.MatchStatistic

class RegularResult(
    private val winner: Player,
    private val loser: Player
): MatchResult(winner, loser) {
    override fun toStatistic() = MatchStatistic(
        player1 = winner.toString(), player2 = loser.toString(), winner = winner.toString(), loser = loser.toString()
    )
    override fun toString() = "$winner beat $loser"
    override fun sameResult(other: MatchResult) = other is RegularResult &&
            (winner == other.winner && loser == other.loser)
}