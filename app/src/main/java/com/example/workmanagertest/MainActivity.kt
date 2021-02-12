package com.example.workmanagertest

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.d(this,"MainActivity.onCreate()")
        findViewById<Button>(R.id.enqueueFromMain).setOnClickListener { SampleWorker.enqueueWork(this) }
        findViewById<Button>(R.id.enqueueFromRemote).setOnClickListener {  sendBroadcast(Intent(this, SecondProcessBroadcastReceiver::class.java))  }
    }
}