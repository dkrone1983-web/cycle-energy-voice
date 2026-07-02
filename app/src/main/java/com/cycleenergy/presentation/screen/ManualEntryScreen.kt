package com.cycleenergy.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cycleenergy.CycleEnergyApp
import com.cycleenergy.domain.model.CyclePhase
import com.cycleenergy.domain.model.TimeOfDay
import com.cycleenergy.presentation.navigation.Screen
import com.cycleenergy.presentation.viewmodel.ConfirmCheckInViewModel

@Composable
fun ManualEntryScreen(navController: NavHostController) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val cycleApp = context.applicationContext as CycleEnergyApp

    val viewModel: ConfirmCheckInViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return ConfirmCheckInViewModel(cycleApp.checkInRepository) as T
            }
        }
    )

    val energyLevel = viewModel.energyLevel.collectAsState()
    val cyclePhase = viewModel.cyclePhase.collectAsState()
    val timeOfDay = viewModel.timeOfDay.collectAsState()
    val mood = viewModel.mood.collectAsState()
    val stressLevel = viewModel.stressLevel.collectAsState()
    val isSaving = viewModel.isSaving.collectAsState()
    val saveSuccess = viewModel.saveSuccess.collectAsState()

    if (saveSuccess.value) {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.ManualEntry.route) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Manuelle Eingabe",
                style = MaterialTheme.typography.displaySmall
            )
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(8.dp),
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
        }

        // Energy Level Slider
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Energielevel: ${energyLevel.value ?: 5}/10", style = MaterialTheme.typography.titleMedium)
                Slider(
                    value = (energyLevel.value ?: 5).toFloat(),
                    onValueChange = { viewModel.updateEnergyLevel(it.toInt()) },
                    valueRange = 1f..10f,
                    steps = 9,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Cycle Phase Selection
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Zyklusphase:", style = MaterialTheme.typography.titleMedium)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CyclePhase.values().filter { it != CyclePhase.UNKNOWN }.forEach { phase ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = cyclePhase.value == phase,
                                onClick = { viewModel.updateCyclePhase(phase) }
                            )
                            Text(
                                text = phase.displayName,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        // Time of Day Selection
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tageszeit:", style = MaterialTheme.typography.titleMedium)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TimeOfDay.values().filter { it != TimeOfDay.UNKNOWN }.forEach { time ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = timeOfDay.value == time,
                                onClick = { viewModel.updateTimeOfDay(time) }
                            )
                            Text(
                                text = time.displayName,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        // Stress Level Slider
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Stresslevel: ${stressLevel.value ?: 5}/10", style = MaterialTheme.typography.titleMedium)
                Slider(
                    value = (stressLevel.value ?: 5).toFloat(),
                    onValueChange = { viewModel.updateStressLevel(it.toInt()) },
                    valueRange = 1f..10f,
                    steps = 9,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Save Button
        Button(
            onClick = { viewModel.saveCheckIn() },
            enabled = !isSaving.value,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isSaving.value) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text("Speichern", style = MaterialTheme.typography.labelLarge)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
