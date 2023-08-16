package no.itverket.rpc.match

class Match(
    private val player1: Player,
    private val player2: Player
) {
    fun startMatch(): Result = (player1 play player2)?.let { winner ->
        Result.results(winner, opponent(winner))
    } ?: Result.tied()

    private fun opponent(player: Player) = if (player == player1) player2 else player1
}