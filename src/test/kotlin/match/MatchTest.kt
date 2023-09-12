package match

import no.itverket.rpc.match.Match
import no.itverket.rpc.match.Player
import no.itverket.rpc.sign.Rock
import no.itverket.rpc.sign.Scissor
import no.itverket.rpc.statistics.MatchStatistic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MatchTest {

    @Test
    fun `match between two players returns correct winner`() {
        val winner = Player("bin", Rock())
        val loser = Player("th", Scissor())
        val match = Match(winner, loser)
        val expectedResult = MatchStatistic.results(winner, loser)
        assertEquals(expectedResult.toString(), match.startMatch().toString())
    }

    @Test
    fun `match between two equal players returns correct tie`() {
        val winner = Player("bin", Rock())
        val loser = Player("th", Rock())
        val match = Match(winner, loser)
        val expectedResult = MatchStatistic.tied()
        assertEquals(expectedResult.toString(), match.startMatch().toString())
    }

    @Test
    fun `matches are equal if players are equal`() {
        val player1 = Player("bin", Rock())
        val player2 = Player("th", Rock())
        val expectedMatch = Match(player1, player2)
        assertEquals(expectedMatch, Match(player1, player2))
    }

    @Test
    fun `matches are equal regardless of order`() {
        val player1 = Player("bin", Rock())
        val player2 = Player("th", Rock())
        val expectedMatch = Match(player1, player2)
        assertEquals(expectedMatch, Match(player2, player1))
    }
}