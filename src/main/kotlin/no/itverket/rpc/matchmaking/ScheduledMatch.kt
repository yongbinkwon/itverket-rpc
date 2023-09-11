package no.itverket.rpc.matchmaking

import jakarta.persistence.*

@Entity
@Table(name = "ScheduledMatch")
data class ScheduledMatch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val sign: String,

    @Column(nullable = false)
    val team: String,

    @Column(nullable = false)
    val processed: Boolean = false
)