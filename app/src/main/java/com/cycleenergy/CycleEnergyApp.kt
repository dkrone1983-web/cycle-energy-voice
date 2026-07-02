package com.cycleenergy

import android.app.Application
import com.cycleenergy.data.db.CycleDatabase
import com.cycleenergy.data.datastore.AppSettings
import com.cycleenergy.data.repository.CheckInRepository
import com.cycleenergy.data.repository.SettingsRepository

class CycleEnergyApp : Application() {

    // Database
    val database by lazy { CycleDatabase.getDatabase(this) }

    // DataStore
    val appSettings by lazy { AppSettings(this) }

    // Repositories
    val checkInRepository by lazy { CheckInRepository(database.checkInDao()) }
    val settingsRepository by lazy { SettingsRepository(appSettings) }
}
