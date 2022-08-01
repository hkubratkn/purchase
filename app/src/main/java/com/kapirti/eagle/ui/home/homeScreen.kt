package com.kapirti.eagle.ui.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.kapirti.eagle.R
import com.kapirti.eagle.data.MyService
import android.app.Activity
import android.content.Intent
import android.net.Uri

@Composable
fun HomeScreen() {
    NavigationDrawer()
    BodyContent()
}

@Composable
fun BodyContent() {
    val context = LocalContext.current
    var navigateClick by remember { mutableStateOf(false) }
    val offSetAnim by animateDpAsState(targetValue = if (navigateClick) 253.dp else 0.dp)
    val scaleAnim by animateFloatAsState(targetValue = if (navigateClick) 0.6f else 1.0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scale(scaleAnim)
            .offset(x = offSetAnim)
            .clip(if (navigateClick) RoundedCornerShape(30.dp) else RoundedCornerShape(0.dp))
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                contentDescription = stringResource(id = R.string.menu),
                modifier = Modifier
                    .clickable { navigateClick = !navigateClick }
                    .padding(15.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = MyService.timeForCounter.value,
                color = MaterialTheme.colors.primary,
                fontSize = 50.sp,
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.three_times_twenty),
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colors.error,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            AndroidView(
                factory = {
                    val aview = AdView(it)
                    aview.apply {
                        aview.setAdSize(AdSize.MEDIUM_RECTANGLE)
                        adUnitId = "ca-app-pub-7130221583141803/6072199463"
                        loadAd(AdRequest.Builder().build())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(0.5f).padding(5.dp),
                    enabled = MyService.btnStateStop.value,
                    onClick = {
                        MyService.stopService(context)
                    }
                ) { Text(text = stringResource(id = R.string.stop)) }
                Button(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    enabled = MyService.btnStateStart.value,
                    onClick = {
                        MyService.startService(context, "service start")
                    }
                ) { Text(text = stringResource(id = R.string.start)) }

            }

        }

    }

}

@Composable
fun NavigationDrawer() {
    val activity = (LocalContext.current as? Activity)
    val context = LocalContext.current
    val shareIntent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.share_code))) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        NavigationItem(
            resId = R.drawable.ic_baseline_star_rate_24,
            text = stringResource(id = R.string.rate),
            itemClicked = { context.startActivity(shareIntent) },
            topPadding = 145.dp
        )
        NavigationItem(
            resId = R.drawable.ic_baseline_share_24,
            text = stringResource(id = R.string.share),
            itemClicked = {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_code))
                intent.type = "text/plain"
                context.startActivity(
                    Intent.createChooser(
                        intent,
                        context.getString(R.string.app_name)
                    )
                )
            }
        )


        Row(
            modifier = Modifier
                .padding(start = 50.dp, bottom = 87.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            TextButton(
                onClick = {
                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_code))
                    intent.type = "text/plain"
                    activity!!.startActivity(
                        Intent.createChooser(
                            intent,
                            context.getString(R.string.app_name)
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.share),
                    color = Color.White,
                    fontSize = 17.sp
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    resId : Int,
    text : String,
    topPadding : Dp = 20.dp,
    destination : String = "",
    itemClicked : (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 38.dp, top = topPadding)
            .clickable { itemClicked(destination) }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 35.dp, top = 26.dp, bottom = 16.dp)
                .size(120.dp, 0.5.dp)
                .background(Color.Gray)
        )
    }

}