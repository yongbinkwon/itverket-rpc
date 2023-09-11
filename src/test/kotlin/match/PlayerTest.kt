package match

import no.itverket.rpc.match.Match
import no.itverket.rpc.match.Player
import no.itverket.rpc.sign.Paper
import no.itverket.rpc.sign.Rock
import no.itverket.rpc.sign.Scissor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `player1 wins over player2 returns player1`() {
        val player1 = Player("bin", Scissor())
        val player2 = Player("th", Paper())
        val winner = player1 play player2
        assertEquals(player1, winner)
    }

    @Test
    fun `player1 loses to player2 returns player2`() {
        val player1 = Player("th", Rock())
        val player2 = Player("bin", Paper())
        val winner = player1 play player2
        assertEquals(player2, winner)
    }

    @Test
    fun `player1 ties player2 returns null`() {
        val player1 = Player("bin", Rock())
        val player2 = Player("th", Rock())
        val winner = player1 play player2
        assertEquals(null, winner)
    }

    @Test
    fun `player1 versus player2 returns Match between them`() {
        val player1 = Player("bin", Rock())
        val player2 = Player("th", Paper())
        val expectedMatch = Match(player1, player2)
        val match = player1 versus player2
        assertEquals(expectedMatch, match)
    }
}