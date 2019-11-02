package com.example.studyapp

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


class RingtonePlayingService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private var curStartId: Int = 0
    private var isRunning: Boolean = false
    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        val notificationHelper: NotificationHelper = NotificationHelper(this)
        val nb: NotificationCompat.Builder = notificationHelper.getChannel1Notification("1", "b")
        val notification: Notification = nb.build()
        notificationHelper.getManager().notify(1, notification)
        startForeground(1, notification)
    }

//    override fun onCreate() {
//        super.onCreate()
////        startForeground(1, )
//
//
//    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        val getState: String = intent.extras.getString("state") as String
        Log.d(TAG, "onStartCommand")

        var getState: String = intent.getStringExtra("state")
        when(getState) {
            "alarm on" -> {
                curStartId = 1
                Log.d(TAG, "Alarm is on")
            }
            "alarm off" -> {
                curStartId = 0
                Log.d(TAG, "Alarm is off")
            }
            else -> {
                curStartId = 0
                Log.d(TAG, "Default")
            }
        }
        Log.d(TAG, String.format("isRunning is %b and curStartId is %d", isRunning ,curStartId))
        // Alarm not running, needs to start
        if (!this.isRunning && curStartId == 1) {
            Log.d(TAG, "not running, id==1")
            mediaPlayer = MediaPlayer.create(this, R.raw.coconut)
            mediaPlayer.start()
            Log.d(TAG, "Start media player")
            this.isRunning = true
            this.curStartId = 0
        }
        // Alarm is playing,
        else if (this.isRunning && curStartId == 0) {
            Log.d(TAG, "running, id==0")
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()

            this.isRunning = false
            this.curStartId = 0
        }


//        return super.onStartCommand(intent, flags, startId)
        return START_NOT_STICKY
    }

}