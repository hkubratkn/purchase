package com.kapirti.eagle

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kapirti.eagle.ui.theme.EagleTheme
import com.google.android.play.core.review.ReviewManagerFactory
import com.kapirti.eagle.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EagleTheme {
                HomeScreen()
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