package com.example.timetablemanager.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat


import androidx.core.app.NotificationCompat

import androidx.navigation.ActivityNavigator
import com.example.timetablemanager.R

class AlarmReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(p0: Context?, p1: Intent?) {


        val i=Intent(p0,Destination::class.java)
        p1!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent=PendingIntent.getActivity(p0,0,i,PendingIntent.FLAG_MUTABLE)

        val builder=NotificationCompat.Builder(p0!!,"TimeTable Manager")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("TimeTable Manager")
            .setContentText("End of the task")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val NotificationManager=NotificationManagerCompat.from(p0)
        NotificationManager.notify(54321,builder)

    }
}