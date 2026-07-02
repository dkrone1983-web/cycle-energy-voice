package com.cycleenergy.core.util

import com.cycleenergy.domain.model.CheckIn
import java.time.format.DateTimeFormatter

object CsvExporter {

    fun exportToCsv(checkIns: List<CheckIn>): String {
        val header = "Datum,Uhrzeit,Energielevel,Zyklusphase,Tageszeit,Stimmung,Stresslevel,Symptome\n"

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

        val rows = checkIns.map { checkIn ->
            val symptoms = checkIn.symptoms.joinToString("; ")
            "${checkIn.timestamp.format(formatter)},${checkIn.energyLevel},${checkIn.cyclePhase.displayName},${checkIn.timeOfDay.displayName},${checkIn.mood},${checkIn.stressLevel},\"$symptoms\""
        }

        return header + rows.joinToString("\n")
    }
}
