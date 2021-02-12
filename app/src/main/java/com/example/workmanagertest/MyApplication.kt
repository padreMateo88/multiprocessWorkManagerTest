package com.example.workmanagertest

import android.app.Application
import androidx.work.Configuration

class MyApplication : Application(), Configuration.Provider {

   override fun getWorkManagerConfiguration(): Configuration {
   Util.d(this, "MyApplication.getWorkManagerConfiguration()")
      return   WorkConfigurationProvider().get()
   }
}