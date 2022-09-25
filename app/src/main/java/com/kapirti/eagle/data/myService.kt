package com.kapirti.eagle.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kapirti.eagle.MainActivity
import com.kapirti.eagle.R

class MyService: Service() {
    private val CHANNEL_ID = "foreground_service"

    companion object {
        var timeForCounter = mutableStateOf("0")
        var btnStateStart = mutableStateOf(true)
        var btnStateStop = mutableStateOf(false)

        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, MyService::class.java)
            startIntent.putExtra("inputtext", message)
            ContextCompat.startForegroundService(context, startIntent)
        }
        fun stopService(context: Context) {
            val stopIntent = Intent(context, MyService::class.java)
            context.stopService(stopIntent)
        }

    }

    @OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("title")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_baseline_panorama_fish_eye_24)
            .setContentIntent(pendingIntent)
            .build()

        object : CountDownTimer(1200000, 1000) {
            val mediaPlayer = MediaPlayer.create(
                applicationContext,
                R.raw.breakmsc
            )
            override fun onTick(p0: Long) {
                MyService.btnStateStart.value = false
                MyService.btnStateStop.value = true
                timeForCounter.value = "Minutes: ${p0 / 1000}"
            }

            override fun onFinish() {
                timeForCounter.value = "0"
                mediaPlayer.start()
                Thread.sleep(20000)
                mediaPlayer.stop()
                MyService.stopService(applicationContext)
                MyService.btnStateStart.value = true
                MyService.btnStateStop.value = false
            }
        }.start()

        startForeground(1, notification)

        return START_NOT_STICKY
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "my service channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

}