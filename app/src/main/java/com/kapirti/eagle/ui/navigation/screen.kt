package com.kapirti.eagle.ui.navigation

sealed class Screen(val route: String) {
    object WelcomeS: Screen(route = "welcome_screen")
    object HomeS: Screen(route = "home_screen")
}