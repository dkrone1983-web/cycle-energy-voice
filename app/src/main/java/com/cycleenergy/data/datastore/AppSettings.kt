package com.cycleenergy.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

class AppSettings(private val context: Context) {
    companion object {
        private val CYCLE_LENGTH_DAYS = intPreferencesKey("cycle_length_days")
        private val MENSTRUATION_LENGTH_DAYS = intPreferencesKey("menstruation_length_days")
        private val LAST_MENSTRUATION_DATE = stringPreferencesKey("last_menstruation_date")
        private val IS_ONBOARDED = booleanPreferencesKey("is_onboarded")
    }

    val cycleLengthDays: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[CYCLE_LENGTH_DAYS] ?: 28
    }

    val menstruationLengthDays: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[MENSTRUATION_LENGTH_DAYS] ?: 5
    }

    val lastMenstruationDate: Flow<LocalDate?> = context.dataStore.data.map { prefs ->
        prefs[LAST_MENSTRUATION_DATE]?.let { dateStr ->
            try {
                LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE)
            } catch (e: Exception) {
                null
            }
        }
    }

    val isOnboarded: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[IS_ONBOARDED] ?: false
    }

    suspend fun saveCycleSettings(
        cycleLengthDays: Int,
        menstruationLengthDays: Int,
        lastMenstruationDate: LocalDate
    ) {
        context.dataStore.edit { prefs ->
            prefs[CYCLE_LENGTH_DAYS] = cycleLengthDays
            prefs[MENSTRUATION_LENGTH_DAYS] = menstruationLengthDays
            prefs[LAST_MENSTRUATION_DATE] = lastMenstruationDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
            prefs[IS_ONBOARDED] = true
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
