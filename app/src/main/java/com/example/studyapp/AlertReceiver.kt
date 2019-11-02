package com.example.studyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

val TAG: String = "THREE_MEAL"
class AlertReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
//        val notificationHelper: NotificationHelper = NotificationHelper(context)
//        val nb: NotificationCompat.Builder = notificationHelper.getChannel1Notification("1", "b")
//        notificationHelper.getManager().notify(1, nb.build())

        val service_intent:Intent = Intent(context, RingtonePlayingService::class.java)
        service_intent.putExtra("state", "alarm on")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "started ringtonePlayingService foreground")
            context.startForegroundService(service_intent)
        } else {
            context.startService(service_intent)
        }
    }
}