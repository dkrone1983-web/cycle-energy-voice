package com.cycleenergy.domain.model

import java.time.LocalDate

data class CycleSettings(
    val cycleLengthDays: Int = 28,
    val menstruationLengthDays: Int = 5,
    val lastMenstruationStartDate: LocalDate? = null,
    val isOnboarded: Boolean = false
)
