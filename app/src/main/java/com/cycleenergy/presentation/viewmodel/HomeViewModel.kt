package com.cycleenergy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cycleenergy.data.repository.CheckInRepository
import com.cycleenergy.data.repository.SettingsRepository
import com.cycleenergy.domain.model.CheckIn
import com.cycleenergy.domain.model.CycleSettings
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val checkInRepository: CheckInRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val recentCheckIns: Flow<List<CheckIn>> = checkInRepository.getRecentCheckInsFlow(3)
    val cycleSettings: Flow<CycleSettings> = settingsRepository.cycleSettingsFlow

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.cycleSettingsFlow.collect { settings ->
                if (settings.isOnboarded) {
                    _uiState.value = HomeUiState.Content
                } else {
                    _uiState.value = HomeUiState.NeedOnboarding
                }
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    object NeedOnboarding : HomeUiState()
    object Content : HomeUiState()
}
