package no.itverket.rpc.match

import no.itverket.rpc.sign.Sign

class Player(
    private val name: String,
    private val sign: Sign
) {
    infix fun play(opponent: Player) = compare(opponent)?.let { if (it) this else opponent }

    infix fun versus(opponent: Player) = Match(this, opponent)

    private fun compare(other: Player) =
        if (sign == other.sign) null
        else sign > other.sign

    override fun toString() = name
}