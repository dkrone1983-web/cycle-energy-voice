package com.cycleenergy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cycleenergy.data.repository.CheckInRepository
import com.cycleenergy.domain.model.CheckIn
import com.cycleenergy.domain.model.CyclePhase
import com.cycleenergy.domain.model.TimeOfDay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class InsightData(
    val avgEnergyByPhase: Map<CyclePhase, Double> = emptyMap(),
    val avgEnergyByTimeOfDay: Map<TimeOfDay, Double> = emptyMap(),
    val avgStressByPhase: Map<CyclePhase, Double> = emptyMap(),
    val heatmapData: HeatmapData = HeatmapData()
)

data class HeatmapData(
    val data: Map<Pair<CyclePhase, TimeOfDay>, Double> = emptyMap()
)

class InsightsViewModel(
    private val checkInRepository: CheckInRepository
) : ViewModel() {

    private val _insightData = MutableStateFlow<InsightData>(InsightData())
    val insightData: StateFlow<InsightData> = _insightData.asStateFlow()

    init {
        viewModelScope.launch {
            checkInRepository.getAllCheckInsFlow().collect { checkIns ->
                _insightData.value = calculateInsights(checkIns)
            }
        }
    }

    private fun calculateInsights(checkIns: List<CheckIn>): InsightData {
        // Average energy by cycle phase
        val avgEnergyByPhase = checkIns
            .groupBy { it.cyclePhase }
            .mapValues { (_, checks) ->
                if (checks.isEmpty()) 0.0 else checks.map { it.energyLevel }.average()
            }

        // Average energy by time of day
        val avgEnergyByTimeOfDay = checkIns
            .groupBy { it.timeOfDay }
            .mapValues { (_, checks) ->
                if (checks.isEmpty()) 0.0 else checks.map { it.energyLevel }.average()
            }

        // Average stress by phase
        val avgStressByPhase = checkIns
            .groupBy { it.cyclePhase }
            .mapValues { (_, checks) ->
                if (checks.isEmpty()) 0.0 else checks.map { it.stressLevel }.average()
            }

        // Heatmap: average energy by phase x time of day
        val heatmapMap = mutableMapOf<Pair<CyclePhase, TimeOfDay>, Double>()
        for (phase in CyclePhase.values()) {
            for (time in TimeOfDay.values()) {
                val relevant = checkIns.filter { it.cyclePhase == phase && it.timeOfDay == time }
                val avg = if (relevant.isEmpty()) 0.0 else relevant.map { it.energyLevel }.average()
                heatmapMap[Pair(phase, time)] = avg
            }
        }

        return InsightData(
            avgEnergyByPhase = avgEnergyByPhase,
            avgEnergyByTimeOfDay = avgEnergyByTimeOfDay,
            avgStressByPhase = avgStressByPhase,
            heatmapData = HeatmapData(heatmapMap)
        )
    }
}
