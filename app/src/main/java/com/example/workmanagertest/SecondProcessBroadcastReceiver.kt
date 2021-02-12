package com.example.workmanagertest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SecondProcessBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Util.d(context,"SecondProcessBroadcastReceiver.onReceive()")
        SampleWorker.enqueueWork(context)
    }
}