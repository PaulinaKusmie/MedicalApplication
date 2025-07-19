package com.example.composeactivity.viewmodel

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.composeactivity.R

class NotificationReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        val channelId = "my_channel_id"

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.medical) // zamień na własną ikonę
            .setContentTitle("Twoja wizyta")
            .setContentText("Czu już umówiłeś swoją wizytę do!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1234, builder.build())
    }
}
