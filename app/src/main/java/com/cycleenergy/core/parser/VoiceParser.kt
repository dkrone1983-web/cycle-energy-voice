package com.cycleenergy.core.parser

import com.cycleenergy.domain.model.CyclePhase
import com.cycleenergy.domain.model.TimeOfDay

data class ParseResult(
    val energyLevel: Int? = null,
    val cyclePhase: CyclePhase = CyclePhase.UNKNOWN,
    val timeOfDay: TimeOfDay = TimeOfDay.UNKNOWN,
    val mood: String = "neutral",
    val stressLevel: Int? = null,
    val symptoms: List<String> = emptyList(),
    val confidence: Float = 0.5f
)

object VoiceParser {

    /**
     * Parses a German voice input like:
     * "Energie 3, Lutealphase, abends, müde, Stress 4, Kopfschmerzen"
     * and returns structured data
     */
    fun parse(input: String): ParseResult {
        val lowerInput = input.lowercase()

        val energyLevel = extractEnergyLevel(lowerInput)
        val cyclePhase = extractCyclePhase(lowerInput)
        val timeOfDay = extractTimeOfDay(lowerInput)
        val mood = extractMood(lowerInput)
        val stressLevel = extractStressLevel(lowerInput)
        val symptoms = extractSymptoms(lowerInput)

        // Confidence: +0.1 for each successfully parsed field
        var confidence = 0.1f
        if (energyLevel != null) confidence += 0.15f
        if (cyclePhase != CyclePhase.UNKNOWN) confidence += 0.15f
        if (timeOfDay != TimeOfDay.UNKNOWN) confidence += 0.15f
        if (mood != "neutral") confidence += 0.15f
        if (stressLevel != null) confidence += 0.15f
        if (symptoms.isNotEmpty()) confidence += 0.1f

        return ParseResult(
            energyLevel = energyLevel,
            cyclePhase = cyclePhase,
            timeOfDay = timeOfDay,
            mood = mood,
            stressLevel = stressLevel,
            symptoms = symptoms,
            confidence = confidence.coerceIn(0f, 1f)
        )
    }

    private fun extractEnergyLevel(input: String): Int? {
        val regex = Regex("""energie\s*[:]?\s*(\d+)""")
        val match = regex.find(input)
        return match?.groupValues?.get(1)?.toIntOrNull()?.coerceIn(1, 10)
    }

    private fun extractCyclePhase(input: String): CyclePhase {
        val phaseKeywords = mapOf(
            "menstruation" to CyclePhase.MENSTRUATION,
            "periode" to CyclePhase.MENSTRUATION,
            "blutung" to CyclePhase.MENSTRUATION,
            "follikulär" to CyclePhase.FOLLICULAR,
            "follikulare" to CyclePhase.FOLLICULAR,
            "follicular" to CyclePhase.FOLLICULAR,
            "eisprung" to CyclePhase.OVULATION,
            "ovulation" to CyclePhase.OVULATION,
            "luteal" to CyclePhase.LUTEAL,
            "lutealphase" to CyclePhase.LUTEAL
        )

        for ((keyword, phase) in phaseKeywords) {
            if (input.contains(keyword)) {
                return phase
            }
        }

        return CyclePhase.UNKNOWN
    }

    private fun extractTimeOfDay(input: String): TimeOfDay {
        val timeKeywords = mapOf(
            "morgens" to TimeOfDay.MORNING,
            "morning" to TimeOfDay.MORNING,
            "morgen" to TimeOfDay.MORNING,
            "früh" to TimeOfDay.MORNING,
            "frühs" to TimeOfDay.MORNING,
            "nachmittags" to TimeOfDay.AFTERNOON,
            "nachmittag" to TimeOfDay.AFTERNOON,
            "afternoon" to TimeOfDay.AFTERNOON,
            "mittags" to TimeOfDay.AFTERNOON,
            "mittag" to TimeOfDay.AFTERNOON,
            "abends" to TimeOfDay.EVENING,
            "abend" to TimeOfDay.EVENING,
            "evening" to TimeOfDay.EVENING,
            "nachts" to TimeOfDay.NIGHT,
            "nacht" to TimeOfDay.NIGHT,
            "night" to TimeOfDay.NIGHT,
            "nachtens" to TimeOfDay.NIGHT
        )

        for ((keyword, time) in timeKeywords) {
            if (input.contains(keyword)) {
                return time
            }
        }

        return TimeOfDay.UNKNOWN
    }

    private fun extractMood(input: String): String {
        val moodKeywords = listOf(
            "müde" to "müde",
            "ausgeruht" to "ausgeruht",
            "energiegeladen" to "energiegeladen",
            "neutral" to "neutral",
            "happy" to "glücklich",
            "glücklich" to "glücklich",
            "traurig" to "traurig",
            "entspannt" to "entspannt",
            "angespannt" to "angespannt",
            "motiviert" to "motiviert",
            "unmotiviert" to "unmotiviert",
            "reizbar" to "reizbar",
            "ängstlich" to "ängstlich"
        )

        for ((keyword, mood) in moodKeywords) {
            if (input.contains(keyword)) {
                return mood
            }
        }

        return "neutral"
    }

    private fun extractStressLevel(input: String): Int? {
        val regex = Regex("""stress\s*[:]?\s*(\d+)""")
        val match = regex.find(input)
        return match?.groupValues?.get(1)?.toIntOrNull()?.coerceIn(1, 10)
    }

    private fun extractSymptoms(input: String): List<String> {
        val symptomKeywords = listOf(
            "kopfschmerzen", "kopfweh", "kopfwehe",
            "müdigkeit", "müde",
            "krämpfe", "krampf", "bauchkrämpfe",
            "aufgebläht", "blähungen", "aufgeblasen",
            "übelkeit", "üble",
            "rückenschmerzen", "rückenweh",
            "brustspannen", "spannen der brust",
            "akne", "pickeln", "pickel",
            "stimmungsschwankungen", "launenhaft",
            "schlaflosigkeit", "schlafstörungen", "schlaflos",
            "wassereeinlagerungen", "wasser",
            "hungerattacken", "hunger", "heißhunger"
        )

        val found = mutableListOf<String>()
        for (symptom in symptomKeywords) {
            if (input.contains(symptom)) {
                // Capitalize first letter for display
                found.add(symptom.replaceFirstChar { it.uppercase() })
            }
        }

        return found.distinct()
    }
}
