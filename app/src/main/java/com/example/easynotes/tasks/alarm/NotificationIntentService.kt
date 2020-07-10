package com.example.easynotes.tasks.alarm

import android.app.AlarmManager
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.easynotes.model.Frequency
import com.example.easynotes.model.Note
import com.google.gson.Gson
import java.util.*

class NotificationIntentService : IntentService("createNotification"){

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onHandleIntent(intent: Intent?) {
        var note : Note = intent?.extras?.getSerializable("note") as Note

        var calendar : Calendar = Calendar.getInstance()
        calendar.time = note.alert

        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        var intent: Intent = Intent(this, ReceiverAlarm::class.java)

        var gson : Gson = Gson()
        intent.action = note.id.toString()
        intent.putExtra("note", gson.toJson(note))

        var pendingIntent : PendingIntent = PendingIntent.getBroadcast(applicationContext,1,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        when(note.frequency){
            Frequency.UNIQUE -> {
                alarmManager?.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis, pendingIntent)
            }
            Frequency.HOUR->{
                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_HOUR,
                    pendingIntent
                )
            }
            Frequency.DIARY->{
                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
            Frequency.WEEK->{
                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }
            Frequency.MONTH->{
                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_HOUR,
                    pendingIntent
                )
            }
        }
    }
}