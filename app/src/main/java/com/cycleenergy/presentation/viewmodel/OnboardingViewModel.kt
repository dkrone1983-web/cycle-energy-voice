package com.cycleenergy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cycleenergy.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class OnboardingViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _cycleLength = MutableStateFlow(28)
    val cycleLength: StateFlow<Int> = _cycleLength.asStateFlow()

    private val _menstruationLength = MutableStateFlow(5)
    val menstruationLength: StateFlow<Int> = _menstruationLength.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    fun updateCycleLength(length: Int) {
        _cycleLength.value = length.coerceIn(21, 35)
    }

    fun updateMenstruationLength(length: Int) {
        _menstruationLength.value = length.coerceIn(1, 7)
    }

    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            _isSaving.value = true
            settingsRepository.saveCycleSettings(
                cycleLengthDays = _cycleLength.value,
                menstruationLengthDays = _menstruationLength.value,
                lastMenstruationDate = _selectedDate.value
            )
            _isSaving.value = false
        }
    }
}
