package com.ibrahimethem.firebasenotificationyazi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.messaging.FirebaseMessaging
import com.ibrahimethem.firebasenotificationyazi.R
import com.ibrahimethem.firebasenotificationyazi.util.Constants.Companion.TOPIC

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
    }
}