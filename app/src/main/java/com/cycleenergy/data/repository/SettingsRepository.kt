package com.cycleenergy.data.repository

import com.cycleenergy.data.datastore.AppSettings
import com.cycleenergy.domain.model.CycleSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.LocalDate

class SettingsRepository(private val appSettings: AppSettings) {

    val cycleSettingsFlow: Flow<CycleSettings> = combine(
        appSettings.cycleLengthDays,
        appSettings.menstruationLengthDays,
        appSettings.lastMenstruationDate,
        appSettings.isOnboarded
    ) { cycleLengthDays, menstruationLengthDays, lastMenstruationDate, isOnboarded ->
        CycleSettings(
            cycleLengthDays = cycleLengthDays,
            menstruationLengthDays = menstruationLengthDays,
            lastMenstruationStartDate = lastMenstruationDate,
            isOnboarded = isOnboarded
        )
    }

    suspend fun saveCycleSettings(
        cycleLengthDays: Int,
        menstruationLengthDays: Int,
        lastMenstruationDate: LocalDate
    ) {
        appSettings.saveCycleSettings(cycleLengthDays, menstruationLengthDays, lastMenstruationDate)
    }

    suspend fun clearAllData() {
        appSettings.clearAllData()
    }
}
