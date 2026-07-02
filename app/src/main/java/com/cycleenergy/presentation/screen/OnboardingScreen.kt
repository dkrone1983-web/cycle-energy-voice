package com.cycleenergy.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cycleenergy.CycleEnergyApp
import com.cycleenergy.presentation.navigation.Screen
import com.cycleenergy.presentation.viewmodel.OnboardingViewModel
import java.time.LocalDate

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val context = LocalContext.current
    val cycleApp = context.applicationContext as CycleEnergyApp
    val settingsRepository = cycleApp.settingsRepository

    val viewModel: OnboardingViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return OnboardingViewModel(settingsRepository) as T
            }
        }
    )

    val cycleLength = viewModel.cycleLength.collectAsState()
    val menstruationLength = viewModel.menstruationLength.collectAsState()
    val selectedDate = viewModel.selectedDate.collectAsState()
    val isSaving = viewModel.isSaving.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Willkommen zu Cycle Energy Voice",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lass uns deine Zyklusinfos einrichten, damit wir deine Energielevel besser verstehen können.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Cycle Length Slider
        Text(
            text = "Zyklislänge: ${cycleLength.value} Tage",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        Slider(
            value = cycleLength.value.toFloat(),
            onValueChange = { viewModel.updateCycleLength(it.toInt()) },
            valueRange = 21f..35f,
            steps = 14,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Menstruation Length Slider
        Text(
            text = "Menstruationsdauer: ${menstruationLength.value} Tage",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        Slider(
            value = menstruationLength.value.toFloat(),
            onValueChange = { viewModel.updateMenstruationLength(it.toInt()) },
            valueRange = 1f..7f,
            steps = 6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Date Selection
        Text(
            text = "Letzter Menstruationsbeginn: ${selectedDate.value}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { /* Date picker would go here */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Datum auswählen")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Complete Button
        Button(
            onClick = {
                viewModel.completeOnboarding()
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            },
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
                Text(
                    text = "Fertig",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
