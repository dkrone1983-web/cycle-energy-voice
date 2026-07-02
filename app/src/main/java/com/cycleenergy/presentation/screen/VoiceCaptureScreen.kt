package com.cycleenergy.presentation.screen

import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cycleenergy.presentation.navigation.Screen
import com.cycleenergy.presentation.viewmodel.VoiceCaptureViewModel

@Composable
fun VoiceCaptureScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: VoiceCaptureViewModel = viewModel()

    val isListening = viewModel.isListening.collectAsState()
    val recognizedText = viewModel.recognizedText.collectAsState()
    val parseResult = viewModel.parseResult.collectAsState()
    val error = viewModel.error.collectAsState()

    var speechRecognizer by remember { mutableStateOf<SpeechRecognizer?>(null) }

    LaunchedEffect(Unit) {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    }

    DisposableEffect(Unit) {
        onDispose {
            speechRecognizer?.destroy()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Close Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Text(
                text = "Sprich dein Energielevel",
                style = MaterialTheme.typography.titleMedium
            )

            Box(modifier = Modifier.size(48.dp)) // Spacer for layout balance
        }

        // Animated Microphone Button
        Box(
            modifier = Modifier
                .size(160.dp)
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isListening.value) {
                // Pulsing circle when listening
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                )
            }

            FloatingActionButton(
                onClick = {
                    if (isListening.value) {
                        speechRecognizer?.stopListening()
                        viewModel.setListening(false)
                    } else {
                        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de-DE")
                            putExtra(RecognizerIntent.EXTRA_PROMPT, "Sag dein Energielevel...")
                        }

                        val listener = object : android.speech.RecognitionListener {
                            override fun onReadyForSpeech(params: android.os.Bundle?) {}
                            override fun onBeginningOfSpeech() {}
                            override fun onRmsChanged(rmsdB: Float) {}
                            override fun onBufferReceived(buffer: ByteArray?) {}
                            override fun onEndOfSpeech() {}
                            override fun onError(error: Int) {
                                val errorMsg = when (error) {
                                    SpeechRecognizer.ERROR_AUDIO -> "Audiofehler"
                                    SpeechRecognizer.ERROR_CLIENT -> "Kundenfehler"
                                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Keine Berechtigung"
                                    SpeechRecognizer.ERROR_NETWORK -> "Netzwerkfehler"
                                    SpeechRecognizer.ERROR_NO_MATCH -> "Keine Treffer"
                                    SpeechRecognizer.ERROR_SERVER -> "Serverfehler"
                                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "Timeout"
                                    else -> "Unbekannter Fehler"
                                }
                                viewModel.onSpeechError(errorMsg)
                                viewModel.setListening(false)
                            }

                            override fun onResults(results: android.os.Bundle?) {
                                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                                if (!matches.isNullOrEmpty()) {
                                    viewModel.onSpeechResult(matches[0])
                                }
                                viewModel.setListening(false)
                            }

                            override fun onPartialResults(partialResults: android.os.Bundle?) {}
                            override fun onEvent(eventType: Int, params: android.os.Bundle?) {}
                        }

                        speechRecognizer?.setRecognitionListener(listener)
                        speechRecognizer?.startListening(intent)
                        viewModel.setListening(true)
                    }
                },
                modifier = Modifier.size(160.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = "Voice Input",
                    modifier = Modifier.size(80.dp)
                )
            }
        }

        // Status Text
        Text(
            text = if (isListening.value) "Höre zu..." else "Bereit zum Sprechen",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Error Display
        error.value?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Text(
                    text = "Fehler: $it",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Recognized Text and Result
        if (recognizedText.value.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Erkannt:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = recognizedText.value,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons for next steps
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.resetForNewAttempt()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text("Erneut versuchen")
                }

                Button(
                    onClick = {
                        // Navigate to confirm screen with parse result
                        navController.navigate(Screen.ConfirmCheckIn.route)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Text("Weiter")
                }
            }
        }
    }
}
