package no.itverket.rpc.match

data class Result constructor(
    private val winner: Player?,
    private val loser: Player?,
) {
    companion object {
        fun tied() = Result(null, null)
        fun results(winner: Player, loser: Player) = Result(winner, loser)
    }

    private fun tied() = winner==null && loser==null
    override fun toString() = if (tied()) "tied game" else "$winner beat $loser"
}