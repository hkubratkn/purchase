package com.kapirti.eagle

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kapirti.eagle.ui.theme.EagleTheme
import com.google.android.play.core.review.ReviewManagerFactory
import com.kapirti.eagle.ui.navigation.BottomBar
import com.kapirti.eagle.ui.navigation.NavGraph
import com.kapirti.eagle.ui.navigation.Screen
import com.kapirti.eagle.ui.presentation.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            EagleTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                var showBottomBar by rememberSaveable{ mutableStateOf(true) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar = when(navBackStackEntry?.destination?.route){
                    Screen.WelcomeS.route -> false

                    else -> true
                }

                Scaffold(
                    modifier = Modifier,
                    bottomBar = {if (showBottomBar) BottomBar()}
                ){ innerPadding ->
                    NavGraph(
                        navController = navController,
                        startDestination = screen,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
            inAppReviewBody(this)
        }
    }
}

private fun inAppReviewBody(context: Context) {
    val manager = ReviewManagerFactory.create(context)
    val request = manager.requestReviewFlow()

    request.addOnCompleteListener { request ->
        if (request.isSuccessful) {
            val reviewInfo = request.result
            val flow = manager.launchReviewFlow(context as Activity, reviewInfo!!)
            flow.addOnCompleteListener { _ ->
            }
        }
    }
}