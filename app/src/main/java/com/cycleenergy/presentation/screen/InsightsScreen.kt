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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cycleenergy.CycleEnergyApp
import com.cycleenergy.domain.model.CyclePhase
import com.cycleenergy.domain.model.TimeOfDay
import com.cycleenergy.presentation.viewmodel.InsightsViewModel

@Composable
fun InsightsScreen(navController: NavHostController) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val cycleApp = context.applicationContext as CycleEnergyApp

    val viewModel: InsightsViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return InsightsViewModel(cycleApp.checkInRepository) as T
            }
        }
    )

    val insightData = viewModel.insightData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Einsichten",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text("Zurück", style = MaterialTheme.typography.labelMedium)
            }
        }

        // Energy by Phase
        if (insightData.value.avgEnergyByPhase.isNotEmpty()) {
            Text(
                text = "Durchschn. Energie nach Zyklusphase",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 12.dp)
            )

            insightData.value.avgEnergyByPhase
                .filter { (phase, _) -> phase != CyclePhase.UNKNOWN }
                .forEach { (phase, avgEnergy) ->
                    InsightBar(
                        label = phase.displayName,
                        value = avgEnergy,
                        max = 10.0
                    )
                }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Energy by Time of Day
        if (insightData.value.avgEnergyByTimeOfDay.isNotEmpty()) {
            Text(
                text = "Durchschn. Energie nach Tageszeit",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 12.dp)
            )

            insightData.value.avgEnergyByTimeOfDay
                .filter { (time, _) -> time != TimeOfDay.UNKNOWN }
                .forEach { (time, avgEnergy) ->
                    InsightBar(
                        label = time.displayName,
                        value = avgEnergy,
                        max = 10.0
                    )
                }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Heatmap
        Text(
            text = "Energieheatmap (Phase × Tageszeit)",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 12.dp)
        )

        HeatmapGrid(insightData.value.heatmapData.data)

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun InsightBar(
    label: String,
    value: Double,
    max: Double = 10.0
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = String.format("%.1f", value),
                style = MaterialTheme.typography.bodySmall
            )
        }

        LinearProgressIndicator(
            progress = (value / max).toFloat().coerceIn(0f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .padding(top = 4.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondaryContainer
        )
    }
}

@Composable
fun HeatmapGrid(
    data: Map<Pair<CyclePhase, TimeOfDay>, Double>
) {
    val phases = listOf(
        CyclePhase.MENSTRUATION,
        CyclePhase.FOLLICULAR,
        CyclePhase.OVULATION,
        CyclePhase.LUTEAL
    )
    val times = listOf(TimeOfDay.MORNING, TimeOfDay.AFTERNOON, TimeOfDay.EVENING, TimeOfDay.NIGHT)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        // Header row for times
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) // Empty corner
            times.forEach { time ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = time.displayName.take(3),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        // Data rows
        phases.forEach { phase ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = phase.displayName.take(3),
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                times.forEach { time ->
                    val value = data[Pair(phase, time)] ?: 0.0
                    val color = getHeatmapColor(value)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(
                                color = color,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (value > 0) {
                            Text(
                                text = String.format("%.1f", value),
                                style = MaterialTheme.typography.labelSmall,
                                color = if (value > 5) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSecondaryContainer
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getHeatmapColor(value: Double): Color {
    return when {
        value <= 0 -> Color(0xFFEEEEEE)
        value < 3 -> Color(0xFFFFCDD2) // Light red
        value < 5 -> Color(0xFFFFB3BA) // Salmon
        value < 7 -> Color(0xFFFFA8A8) // Coral
        value < 9 -> Color(0xFF9B7E9F) // Purple
        else -> Color(0xFF7D5E86) // Dark purple
    }
}
