package no.itverket.rpc.matchmaking

import jakarta.persistence.*
import no.itverket.rpc.match.Player
import no.itverket.rpc.sign.Sign

@Entity
@Table(name = "QueuedPlayer")
class QueuedPlayer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false)
    private val sign: String,

    @Column(nullable = false)
    private val team: String,

    @Column(nullable = false)
    private var processed: Boolean = false
) {
    infix fun findMatch(queuePool: List<QueuedPlayer>) = queuePool.firstOrNull { it.team != this.team }

    infix fun versus(opponent: QueuedPlayer?) = opponent?.let {
        this.matchReadyPlayer() versus it.matchReadyPlayer()
    } ?: (this.matchReadyPlayer() versus bot())

    private fun matchReadyPlayer() = with(this) {
        processed = true
        toPlayer()
    }

    private fun toPlayer() = Player(team, Sign.fromString(sign))

    private fun bot() = Player("bot", Sign.random())
}