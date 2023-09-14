package sign

import no.itverket.rpc.sign.Paper
import no.itverket.rpc.sign.Rock
import no.itverket.rpc.sign.Scissor
import no.itverket.rpc.sign.Sign
import no.itverket.rpc.sign.exception.UnknownSignClassException
import no.itverket.rpc.sign.exception.UnknownSignNameException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.random.Random


class SignTest {

    @Nested
    inner class RockTest {
        @Test
        fun `rock beats scissor`() { assertTrue(Rock() > Scissor()) }

        @Test
        fun `rock ties rock`() { assertTrue(Rock() == Rock()) }

        @Test
        fun `rock loses to paper`() { assertTrue(Rock() < Paper()) }
    }

    @Nested
    inner class ScissorTest {
        @Test
        fun `scissor beats paper`() { assertTrue(Scissor() > Paper()) }

        @Test
        fun `scissor ties scissor`() { assertTrue(Scissor() == Scissor()) }

        @Test
        fun `scissor loses to rock`() { assertTrue(Scissor() < Rock()) }
    }

    @Nested
    inner class PaperTest {
        @Test
        fun `paper beats rock`() { assertTrue(Paper() > Rock()) }

        @Test
        fun `paper ties paper`() { assertTrue(Paper() == Paper()) }

        @Test
        fun `paper loses to scissor`() { assertTrue(Paper() < Scissor()) }
    }

    @Nested
    inner class UnknownSignTest {
        inner class UnknownSign: Sign()

        @Test
        fun `unknown sign throws exception at instantiation`() {
            assertThrows(UnknownSignClassException::class.java) { UnknownSign() }
        }

        @Test
        //this will actually be caught at instantiation as well, so more for future-proofing
        fun `unknown sign throws exception at comparison`() {
            assertThrows(UnknownSignClassException::class.java) { UnknownSign() > Rock() }
        }
    }

    @Nested
    inner class StringToSignTest {
        private val randomGenerator = Random.Default
        private val whitespaces = listOf(" ", "\t", "\n")
        private fun String.randomCapitalization() = map { if (randomGenerator.nextBoolean()) it.uppercase() else it }.joinToString("")
        private fun randomWhitespaces() =
            (0..randomGenerator.nextInt(5)).joinToString(separator = "") { whitespaces.random() }
        private fun String.addRandomWhitespaceAndCapitalization() =
            "${randomWhitespaces()}${randomCapitalization()}${randomWhitespaces()}"

        @Test
        fun `String rock to object Rock`() {
            val rockString = "rock"
            assertEquals(Sign.fromString(rockString), Rock())

            val randomRockString = rockString.addRandomWhitespaceAndCapitalization()
            assertEquals(Sign.fromString(randomRockString), Rock())
        }

        @Test
        fun `String paper to object Paper`() {
            val paperString = "paper"
            assertEquals(Sign.fromString(paperString), Paper())

            val randomRockString = paperString.addRandomWhitespaceAndCapitalization()
            assertEquals(Sign.fromString(randomRockString), Paper())
        }

        @Test
        fun `String scissor to object Scissor`() {
            val scissorString = "scissor"
            assertEquals(Sign.fromString(scissorString), Scissor())

            val randomRockString = scissorString.addRandomWhitespaceAndCapitalization()
            assertEquals(Sign.fromString(randomRockString), Scissor())
        }

        @Test
        fun `unknown sign string throws UnknownSignNameException`() {
            val unknownSignString = "hei"
            assertThrows(UnknownSignNameException::class.java) { Sign.fromString(unknownSignString) }
        }
    }
}