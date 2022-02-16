package com.ibrahimethem.firebasenotificationyazi.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ibrahimethem.firebasenotificationyazi.R
import com.ibrahimethem.firebasenotificationyazi.util.Constants.Companion.arg
import com.ibrahimethem.firebasenotificationyazi.view.MainActivity
import java.util.*

class FirebaseService : FirebaseMessagingService() {
    private val chanelId =  "firebase_chanel"

    private lateinit var pendingIntent : PendingIntent

    override fun onMessageReceived(pNotification : RemoteMessage) {
        super.onMessageReceived(pNotification)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = Random().nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChanel(notificationManager)
        }

        val uuid = pNotification.data["uuid"]
        println("firebase service : $uuid")
        //Uuid boş ise notification btn ile gelinmiştir genel duyuru
        if (uuid == "bos"){
            val intent = Intent(this,MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            pendingIntent = PendingIntent.getActivity(this,0,intent,FLAG_ONE_SHOT)
        }else{
            //uuid bos degilse detaya gidilecektir uuid vardır
            arg.putString("uuid",uuid)
            pendingIntent = NavDeepLinkBuilder(this)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.detailFragment)
                .createPendingIntent()
        }

        val notification = NotificationCompat.Builder(this,chanelId)
            .setContentTitle(pNotification.data["title"])
            .setContentText(pNotification.data["message"])
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId,notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun notificationChanel(notificationManager : NotificationManager){
        val chanelName = "firebaseChannel"
        val channel = NotificationChannel(chanelId,chanelName,IMPORTANCE_HIGH).apply {
            description = "notification kanal"
            enableLights(true)
            lightColor = Color.MAGENTA
        }
        notificationManager.createNotificationChannel(channel)
    }
}