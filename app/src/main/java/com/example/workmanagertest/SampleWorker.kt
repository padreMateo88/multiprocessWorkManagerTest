package com.example.workmanagertest

import android.content.Context
import androidx.work.*
import androidx.work.multiprocess.RemoteWorkManager

class SampleWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Util.d(applicationContext, "SampleWorker.doWork()")
        return Result.success()
    }

    companion object {
        @JvmStatic
        fun enqueueWork(context: Context) {
            Util.d(context,"SampleWorker.enqueueWork()")
            try {
                val rwm = RemoteWorkManager.getInstance(context)
                Util.d(context,"RemoteWorkManager hash ${rwm.hashCode()}")
                rwm.enqueueUniqueWork(
                        "SampleWorker",
                        ExistingWorkPolicy.REPLACE,
                        OneTimeWorkRequest.from(SampleWorker::class.java)
                )
            } catch (ex: Throwable) {
                Util.d(context,"SampleWorker, WorkManager is not initialized properly, reason: " + ex.message)
            }
        }
    }
}