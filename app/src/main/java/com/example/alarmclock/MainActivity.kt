package com.example.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var calendar: Calendar
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendar = Calendar.getInstance()
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        button_setHours.setOnClickListener {
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)

            val hours: String
            val minute: String
            if (timePicker.hour < 10) hours = "0" + timePicker.hour.toString()
            else hours = timePicker.hour.toString()
            if (timePicker.minute < 10) minute = "0" + timePicker.minute.toString()
            else minute = timePicker.minute.toString()
            pendingIntent = PendingIntent.getBroadcast(
                this, 0, Intent(this, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            text_showHours.text = "Giờ đã hẹn: " + hours + ":" + minute
        }
    }
}
