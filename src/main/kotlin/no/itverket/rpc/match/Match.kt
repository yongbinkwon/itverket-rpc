package no.itverket.rpc.match

import no.itverket.rpc.match.result.MatchResult

class Match(
    private val player1: Player,
    private val player2: Player
) {
    fun startMatch() = (player1 play player2)?.let { winner ->
        MatchResult.results(winner, opponent(winner))
    } ?: MatchResult.tie(player1, player2)

    private fun opponent(player: Player) = if (player == player1) player2 else player1

    override fun equals(other: Any?) = this === other || other is Match && this.equals(other)

    private fun equals(other: Match) = equalsSameOrder(other) || equalsDifferentOrder(other)
    private fun equalsSameOrder(other: Match) = player1==other.player1 && player2==other.player2
    private fun equalsDifferentOrder(other: Match) = player1==other.player2 && player2==other.player1

    override fun hashCode(): Int {
        var result = player1.hashCode()
        result = 31 * result + player2.hashCode()
        return result
    }
}