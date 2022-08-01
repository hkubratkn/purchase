package com.kapirti.eagle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kapirti.eagle.ui.theme.EagleTheme
import com.google.android.gms.ads.MobileAds
import com.google.android.play.core.review.ReviewManagerFactory
import com.kapirti.eagle.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MobileAds.initialize(this){}
            val manager = ReviewManagerFactory.create(this)
            val request = manager.requestReviewFlow()

            EagleTheme {
                HomeScreen()
            }

            request.addOnCompleteListener { request ->
                if (request.isSuccessful) {
                    val reviewInfo = request.result
                    val flow = manager.launchReviewFlow(this@MainActivity, reviewInfo!!)
                    flow.addOnCompleteListener { _ ->
                    }
                }
            }


        }
    }
}