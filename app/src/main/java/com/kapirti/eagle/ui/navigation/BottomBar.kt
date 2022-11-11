package com.kapirti.eagle.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BottomBar(){
    AndroidView(
        factory = {
            val aview = AdView(it)
            aview.apply {
                aview.setAdSize(AdSize.BANNER)
                adUnitId = "ca-app-pub-3006196735467220/7093571829"
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}