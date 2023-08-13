package no.itverket.rpc.match

import no.itverket.rpc.hand.Hand

class Player(
    private val hand: Hand
) {
    infix fun play(opponent: Player) = compareHands(opponent)?.let { if (it) this else opponent }

    private fun compareHands(other: Player) =
        if (hand == other.hand) null
        else hand > other.hand
}