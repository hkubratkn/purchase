package com.kapirti.eagle.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kapirti.eagle.ui.presentation.home.HomeScreen
import com.kapirti.eagle.ui.presentation.setting.SettingScreen
import com.kapirti.eagle.ui.presentation.welcome.WelcomeScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.WelcomeS.route) { WelcomeScreen(navController = navController) }
        composable(route = Screen.HomeS.route) { HomeScreen(modifier = modifier, navController = navController) }
        composable(route = Screen.SettingS.route){ SettingScreen(modifier = modifier) }
    }
}