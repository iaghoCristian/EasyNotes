package com.example.easynotes.tasks.alarm

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.easynotes.R
import com.example.easynotes.model.Note

class NotificationHelper(context: Context, note: Note) : ContextWrapper(context) {

    var note: Note
    var notificationManager: NotificationManager? = null

    init {
        this.note = note
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O )
        createChannel()
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun createChannel(){
        var notificationChannel: NotificationChannel = NotificationChannel(note.id.toString(),note.title,NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.lightColor = R.color.colorPrimary
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getManager().createNotificationChannel(notificationChannel)
    }

    fun getManager():NotificationManager{
        if(notificationManager == null){
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return notificationManager as NotificationManager
    }

    fun getNotification(): NotificationCompat.Builder{
        return NotificationCompat.Builder(applicationContext, note.id.toString())
            .setContentTitle(note.title)
            .setContentText(note.resume)
            .setSmallIcon(R.drawable.easynotes_small)
    }

}