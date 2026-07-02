package com.cycleenergy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cycleenergy.core.parser.ParseResult
import com.cycleenergy.data.repository.CheckInRepository
import com.cycleenergy.domain.model.CheckIn
import com.cycleenergy.domain.model.CyclePhase
import com.cycleenergy.domain.model.TimeOfDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ConfirmCheckInViewModel(
    private val checkInRepository: CheckInRepository
) : ViewModel() {

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    private val _saveError = MutableStateFlow<String?>(null)
    val saveError: StateFlow<String?> = _saveError.asStateFlow()

    // Editable fields
    private val _energyLevel = MutableStateFlow<Int?>(null)
    val energyLevel: StateFlow<Int?> = _energyLevel.asStateFlow()

    private val _cyclePhase = MutableStateFlow(CyclePhase.UNKNOWN)
    val cyclePhase: StateFlow<CyclePhase> = _cyclePhase.asStateFlow()

    private val _timeOfDay = MutableStateFlow(TimeOfDay.UNKNOWN)
    val timeOfDay: StateFlow<TimeOfDay> = _timeOfDay.asStateFlow()

    private val _mood = MutableStateFlow("neutral")
    val mood: StateFlow<String> = _mood.asStateFlow()

    private val _stressLevel = MutableStateFlow<Int?>(null)
    val stressLevel: StateFlow<Int?> = _stressLevel.asStateFlow()

    private val _symptoms = MutableStateFlow<List<String>>(emptyList())
    val symptoms: StateFlow<List<String>> = _symptoms.asStateFlow()

    fun initializeFromParseResult(parseResult: ParseResult) {
        _energyLevel.value = parseResult.energyLevel
        _cyclePhase.value = parseResult.cyclePhase
        _timeOfDay.value = parseResult.timeOfDay
        _mood.value = parseResult.mood
        _stressLevel.value = parseResult.stressLevel
        _symptoms.value = parseResult.symptoms
    }

    fun updateEnergyLevel(level: Int) {
        _energyLevel.value = level.coerceIn(1, 10)
    }

    fun updateCyclePhase(phase: CyclePhase) {
        _cyclePhase.value = phase
    }

    fun updateTimeOfDay(time: TimeOfDay) {
        _timeOfDay.value = time
    }

    fun updateMood(newMood: String) {
        _mood.value = newMood
    }

    fun updateStressLevel(level: Int) {
        _stressLevel.value = level.coerceIn(1, 10)
    }

    fun updateSymptoms(newSymptoms: List<String>) {
        _symptoms.value = newSymptoms
    }

    fun saveCheckIn() {
        viewModelScope.launch {
            _isSaving.value = true
            try {
                val energyLevel = _energyLevel.value ?: 5
                val stressLevel = _stressLevel.value ?: 5

                val checkIn = CheckIn(
                    timestamp = LocalDateTime.now(),
                    energyLevel = energyLevel,
                    cyclePhase = _cyclePhase.value,
                    timeOfDay = _timeOfDay.value,
                    mood = _mood.value,
                    stressLevel = stressLevel,
                    symptoms = _symptoms.value
                )

                checkInRepository.saveCheckIn(checkIn)
                _saveSuccess.value = true
            } catch (e: Exception) {
                _saveError.value = e.message ?: "Fehler beim Speichern"
            } finally {
                _isSaving.value = false
            }
        }
    }

    fun resetState() {
        _saveSuccess.value = false
        _saveError.value = null
    }
}
