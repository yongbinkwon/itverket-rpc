package no.itverket.rpc.match

import no.itverket.rpc.hand.Paper
import no.itverket.rpc.hand.Rock
import no.itverket.rpc.hand.Scissor
import org.springframework.stereotype.Service

@Service
class Match {
    companion object {
        private val SIGNS = listOf(Rock(), Paper(), Scissor())
    }

    fun playCheater() {
        val sign = SIGNS.random()
        //call endpoint with sign
    }
}