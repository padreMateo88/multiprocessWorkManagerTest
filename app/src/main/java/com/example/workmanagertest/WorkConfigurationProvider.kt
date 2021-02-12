package com.example.workmanagertest

import androidx.work.Configuration

class WorkConfigurationProvider {
    fun get() = Configuration.Builder().setDefaultProcessName(processName).build()

    companion object {
        private const val processName = "com.example.workmanagertest:second_process"
    }
}