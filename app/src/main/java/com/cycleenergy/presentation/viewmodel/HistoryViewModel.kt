package com.cycleenergy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cycleenergy.data.repository.CheckInRepository
import com.cycleenergy.domain.model.CheckIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val checkInRepository: CheckInRepository
) : ViewModel() {

    val allCheckIns: Flow<List<CheckIn>> = checkInRepository.getAllCheckInsFlow()

    private val _deleteSuccess = MutableStateFlow(false)
    val deleteSuccess: StateFlow<Boolean> = _deleteSuccess.asStateFlow()

    fun deleteCheckIn(checkIn: CheckIn) {
        viewModelScope.launch {
            checkInRepository.deleteCheckIn(checkIn)
        }
    }

    fun deleteAllCheckIns() {
        viewModelScope.launch {
            checkInRepository.deleteAllCheckIns()
            _deleteSuccess.value = true
        }
    }

    fun resetDeleteSuccess() {
        _deleteSuccess.value = false
    }
}
