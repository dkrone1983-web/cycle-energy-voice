package com.cycleenergy.presentation.theme

import androidx.compose.foundation.isSystemInDarkMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Color Palette - Warm, calming, minimal
private val PrimaryLight = Color(0xFF9B7E9F)      // Soft purple
private val SecondaryLight = Color(0xFFC9B1CC)    // Light purple
private val TertiaryLight = Color(0xFFE8D5E8)     // Very light purple
private val ErrorLight = Color(0xFFA85454)        // Muted red
private val BackgroundLight = Color(0xFFFBF8FB)   // Off-white

private val PrimaryDark = Color(0xFFC9B1CC)       // Light purple
private val SecondaryDark = Color(0xFF7D5E86)     // Dark purple
private val TertiaryDark = Color(0xFF9B7E9F)      // Medium purple
private val ErrorDark = Color(0xFFD19999)         // Light red
private val BackgroundDark = Color(0xFF1F1F1F)    // Dark

val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    tertiary = TertiaryLight,
    error = ErrorLight,
    background = BackgroundLight,
    surface = Color.White
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,
    error = ErrorDark,
    background = BackgroundDark,
    surface = Color(0xFF2D2D2D)
)

@Composable
fun CycleEnergyTheme(
    darkTheme: Boolean = isSystemInDarkMode(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
