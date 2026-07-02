package com.cycleenergy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cycleenergy.data.repository.CheckInRepository
import com.cycleenergy.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    private val checkInRepository: CheckInRepository
) : ViewModel() {

    private val _deleteInProgress = MutableStateFlow(false)
    val deleteInProgress: StateFlow<Boolean> = _deleteInProgress.asStateFlow()

    private val _deleteSuccess = MutableStateFlow(false)
    val deleteSuccess: StateFlow<Boolean> = _deleteSuccess.asStateFlow()

    fun deleteAllData() {
        viewModelScope.launch {
            _deleteInProgress.value = true
            try {
                checkInRepository.deleteAllCheckIns()
                settingsRepository.clearAllData()
                _deleteSuccess.value = true
            } finally {
                _deleteInProgress.value = false
            }
        }
    }

    fun resetDeleteSuccess() {
        _deleteSuccess.value = false
    }
}
