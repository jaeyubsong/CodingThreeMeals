package com.example.studyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlertReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationHelper: NotificationHelper = NotificationHelper(context)
        val nb: NotificationCompat.Builder = notificationHelper.getChannel1Notification("1", "b")
        notificationHelper.getManager().notify(1, nb.build())
    }
}