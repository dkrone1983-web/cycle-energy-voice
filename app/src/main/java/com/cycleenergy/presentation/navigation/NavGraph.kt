package com.cycleenergy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cycleenergy.core.parser.ParseResult
import com.cycleenergy.presentation.screen.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object VoiceCapture : Screen("voice_capture")
    object ConfirmCheckIn : Screen("confirm_check_in")
    object ManualEntry : Screen("manual_entry")
    object History : Screen("history")
    object Insights : Screen("insights")
    object Settings : Screen("settings")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.VoiceCapture.route) {
            VoiceCaptureScreen(navController = navController)
        }
        composable(Screen.ConfirmCheckIn.route) {
            ConfirmCheckInScreen(navController = navController)
        }
        composable(Screen.ManualEntry.route) {
            ManualEntryScreen(navController = navController)
        }
        composable(Screen.History.route) {
            HistoryScreen(navController = navController)
        }
        composable(Screen.Insights.route) {
            InsightsScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}
