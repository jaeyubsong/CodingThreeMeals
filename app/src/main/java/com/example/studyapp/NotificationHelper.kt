package com.example.studyapp

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper : ContextWrapper {
    val channel1ID = "channel1ID"
    val channel1Name = "Channel 1"

    val channel2ID = "channel2ID"
    val channel2Name = "Channel 2"

    private lateinit var mManager:NotificationManager

    constructor(base: Context) : super(base) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }


    @TargetApi(Build.VERSION_CODES.O)
    fun createChannels() {
        val channel1 = NotificationChannel(channel1ID, channel1Name, NotificationManager.IMPORTANCE_HIGH)
        channel1.enableLights(true)
        channel1.enableVibration(true)
        channel1.lightColor = R.color.colorPrimary
        channel1.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getManager().createNotificationChannel(channel1)

        val channel2 = NotificationChannel(channel2ID, channel2Name, NotificationManager.IMPORTANCE_HIGH)
        channel2.enableLights(true)
        channel2.enableVibration(true)
        channel2.setLightColor(R.color.colorPrimary)
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE)

        getManager()
        mManager.createNotificationChannel(channel2)
    }

    fun getManager() : NotificationManager {
        if (!::mManager.isInitialized) {
            mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return mManager
    }

    fun getChannel1Notification(title: String, message: String) : NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channel1ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_one)
    }

    fun getChannel2Notification(title: String, message: String) : NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channel2ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_two)
    }
}