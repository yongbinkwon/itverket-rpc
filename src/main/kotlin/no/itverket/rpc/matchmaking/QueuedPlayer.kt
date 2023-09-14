package no.itverket.rpc.matchmaking

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
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