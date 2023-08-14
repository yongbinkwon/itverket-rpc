package no.itverket.rpc.hand

import java.lang.RuntimeException

abstract class Hand: Comparable<Hand> {
    companion object {
        private val SIGN_RELATIONSHIP = listOf(Rock::class, Paper::class, Scissor::class)
    }

    private fun signRelationshipIndex() =
        SIGN_RELATIONSHIP.indexOf(this::class).takeIf { it != -1 } ?: throw RuntimeException("New unknown sign ${this::class}")

    private val weaknessChart = signRelationshipIndex().let {
        mapOf(
            SIGN_RELATIONSHIP[it] to 0,
            SIGN_RELATIONSHIP[(it+1)%3] to -1,
            SIGN_RELATIONSHIP[(it+2)%3] to 1
        )
    }

    override fun compareTo(other: Hand) = weaknessChart[other::class] ?: throw RuntimeException("New unknown sign ${other::class}")
}