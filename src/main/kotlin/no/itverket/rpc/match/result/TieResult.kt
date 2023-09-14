package no.itverket.rpc.match.result

import no.itverket.rpc.match.Player
import no.itverket.rpc.statistics.MatchStatistic

class TieResult(
    private val player1: Player,
    private val player2: Player
): MatchResult() {
    override fun toStatistic() = MatchStatistic(player1 = player1.toString(), player2 = player2.toString())
    override fun toString() = "tied game between $player1 and $player2"
    override fun sameResult(other: MatchResult) = other is TieResult &&
            (player1 == other.player1 && player2 == other.player2 || player1 == other.player2 && player2 == other.player1)
}