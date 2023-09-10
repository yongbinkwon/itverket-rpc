package no.itverket.rpc.sign

import no.itverket.rpc.sign.exception.UnknownSignClassException
import no.itverket.rpc.sign.exception.UnknownSignNameException

abstract class Sign: Comparable<Sign> {
    companion object {
        private val SIGN_RELATIONSHIP = listOf(Rock::class, Paper::class, Scissor::class)
        fun fromString(sign: String) = when(sign.trim().lowercase()) {
            "rock" -> Rock()
            "paper" -> Paper()
            "scissor" -> Scissor()
            else -> throw UnknownSignNameException(sign)
        }
    }

    private fun signRelationshipIndex() =
        SIGN_RELATIONSHIP.indexOf(this::class).takeIf { it != -1 } ?: throw UnknownSignClassException(this::class)

    private val weaknessChart = signRelationshipIndex().let {
        mapOf(
            SIGN_RELATIONSHIP[it] to 0,
            SIGN_RELATIONSHIP[(it+1)%3] to -1,
            SIGN_RELATIONSHIP[(it+2)%3] to 1
        )
    }

    override fun compareTo(other: Sign) = weaknessChart[other::class] ?: throw UnknownSignClassException(this::class)

    override fun equals(other: Any?) = this === other || other is Sign && this.compareTo(other) == 0
}