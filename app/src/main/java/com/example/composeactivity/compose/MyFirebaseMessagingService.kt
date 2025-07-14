package com.example.composeactivity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.composeactivity.R


class MyFirebaseMessagingService : FirebaseMessagingService() {
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        Log.d("FCM", "Wiadomość otrzymana: ${remoteMessage.notification?.body}")
//
//        val title = remoteMessage.data["title"] ?: "Nowa wiadomość"
//        val body = remoteMessage.data["body"] ?: "Brak treści"
//
//        if (remoteMessage.data.isNotEmpty()) {
//            val title = remoteMessage.data["title"] ?: "Nowa wiadomość"
//            val body = remoteMessage.data["body"] ?: "Brak treści"
//            showNotification(title, body)
//        }
//
//        // Jeśli wiadomość ma tylko "notification"
//        remoteMessage.notification?.let {
//            val title = it.title ?: "Powiadomienie"
//            val body = it.body ?: "Treść niedostępna"
//            showNotification(title, body)
//        }
//    }
//
//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//        Log.d("FCM", "FCM token: $token")
//    }
//
//    private fun showNotification(title: String, message: String) {
//        val channelId = "default_channel"
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "FCM Channel",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notification = NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.drawable.medical)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .build()
//
//        notificationManager.notify(0, notification)
//    }

}
