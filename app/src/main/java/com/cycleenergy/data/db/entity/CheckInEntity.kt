package com.cycleenergy.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cycleenergy.domain.model.CyclePhase
import com.cycleenergy.domain.model.TimeOfDay
import java.time.LocalDateTime

@Entity(tableName = "check_ins")
data class CheckInEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long,  // LocalDateTime als milliseconds
    val energyLevel: Int,
    val cyclePhase: String,  // CyclePhase.name
    val timeOfDay: String,   // TimeOfDay.name
    val mood: String,
    val stressLevel: Int,
    val symptomsJson: String  // JSON array of symptoms
)

fun CheckInEntity.toDomain(): com.cycleenergy.domain.model.CheckIn {
    return com.cycleenergy.domain.model.CheckIn(
        id = id,
        timestamp = LocalDateTime.ofEpochSecond(timestamp / 1000, 0, java.time.ZoneOffset.UTC),
        energyLevel = energyLevel,
        cyclePhase = CyclePhase.valueOf(cyclePhase),
        timeOfDay = TimeOfDay.valueOf(timeOfDay),
        mood = mood,
        stressLevel = stressLevel,
        symptoms = try {
            symptomsJson.split(",").filter { it.isNotEmpty() }
        } catch (e: Exception) {
            emptyList()
        }
    )
}

fun com.cycleenergy.domain.model.CheckIn.toEntity(): CheckInEntity {
    return CheckInEntity(
        id = id,
        timestamp = timestamp.atZone(java.time.ZoneId.of("UTC")).toInstant().toEpochMilli(),
        energyLevel = energyLevel,
        cyclePhase = cyclePhase.name,
        timeOfDay = timeOfDay.name,
        mood = mood,
        stressLevel = stressLevel,
        symptomsJson = symptoms.joinToString(",")
    )
}
