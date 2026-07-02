package com.cycleenergy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.cycleenergy.core.parser.ParseResult
import com.cycleenergy.core.parser.VoiceParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VoiceCaptureViewModel : ViewModel() {

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    private val _recognizedText = MutableStateFlow("")
    val recognizedText: StateFlow<String> = _recognizedText.asStateFlow()

    private val _parseResult = MutableStateFlow<ParseResult?>(null)
    val parseResult: StateFlow<ParseResult?> = _parseResult.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun setListening(listening: Boolean) {
        _isListening.value = listening
    }

    fun onSpeechResult(text: String) {
        _recognizedText.value = text
        _parseResult.value = VoiceParser.parse(text)
    }

    fun onSpeechError(errorMessage: String) {
        _error.value = errorMessage
    }

    fun clearError() {
        _error.value = null
    }

    fun resetForNewAttempt() {
        _recognizedText.value = ""
        _parseResult.value = null
        _error.value = null
        _isListening.value = false
    }
}
