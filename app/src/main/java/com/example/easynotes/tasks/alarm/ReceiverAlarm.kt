package com.example.easynotes.tasks.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.easynotes.model.Note
import com.google.gson.Gson

class ReceiverAlarm: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val gson: Gson = Gson()
        var note: Note? = gson.fromJson(intent?.getStringExtra("note"),Note::class.java)

        if(note != null) {
            var notificationHelper = NotificationHelper(context as Context, note)
            var builder: NotificationCompat.Builder = notificationHelper.getNotification()
            notificationHelper.getManager().notify(1, builder.build())
        }
    }
}