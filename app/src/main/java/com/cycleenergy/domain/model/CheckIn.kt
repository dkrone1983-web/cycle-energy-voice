package com.cycleenergy.domain.model

import java.time.LocalDateTime

data class CheckIn(
    val id: Long = 0,
    val timestamp: LocalDateTime,
    val energyLevel: Int,           // 1-10
    val cyclePhase: CyclePhase,
    val timeOfDay: TimeOfDay,
    val mood: String,               // z.B. "müde", "ausgeruht"
    val stressLevel: Int,           // 1-10
    val symptoms: List<String> = emptyList()  // z.B. ["Kopfschmerzen", "Müdigkeit"]
)

enum class CyclePhase(val displayName: String) {
    MENSTRUATION("Menstruation"),
    FOLLICULAR("Follikulär"),
    OVULATION("Eisprung"),
    LUTEAL("Luteal"),
    UNKNOWN("Unbekannt");

    companion object {
        fun fromString(value: String): CyclePhase {
            return when (value.lowercase()) {
                "menstruation", "periode", "blutung" -> MENSTRUATION
                "follikulär", "follikulare", "follicular" -> FOLLICULAR
                "eisprung", "ovulation" -> OVULATION
                "luteal", "lutealphase" -> LUTEAL
                else -> UNKNOWN
            }
        }
    }
}

enum class TimeOfDay(val displayName: String) {
    MORNING("Morgens"),
    AFTERNOON("Nachmittags"),
    EVENING("Abends"),
    NIGHT("Nachts"),
    UNKNOWN("Unbekannt");

    companion object {
        fun fromString(value: String): TimeOfDay {
            return when (value.lowercase()) {
                "morgens", "morning", "morgen", "früh", "frühs" -> MORNING
                "nachmittags", "nachmittag", "afternoon", "mittags", "mittag" -> AFTERNOON
                "abends", "abend", "evening" -> EVENING
                "nachts", "nacht", "night", "nachtens" -> NIGHT
                else -> UNKNOWN
            }
        }
    }
}
