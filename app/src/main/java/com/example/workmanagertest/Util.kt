package com.example.workmanagertest

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build.VERSION
import android.os.Process
import android.util.Log
import androidx.work.impl.background.greedy.GreedyScheduler

object Util {

    private const val TAG = "WorkManagerTest"

    @JvmStatic
    fun d(context: Context,message: String) {
        Log.d(TAG, "process: ${getProcessName(context)} $message")
    }


    /** Copy of androidx.work.impl.utils.ProcessUtils
     * @return The name of the active process.
     */
    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    fun getProcessName(context: Context): String? {
        if (VERSION.SDK_INT >= 28) {
            return Application.getProcessName()
        }

        // Try using ActivityThread to determine the current process name.
        try {
            val activityThread = Class.forName(
                    "android.app.ActivityThread",
                    false,
                    GreedyScheduler::class.java.classLoader)
            val packageName: Any
            if (VERSION.SDK_INT >= 18) {
                val currentProcessName = activityThread.getDeclaredMethod("currentProcessName")
                currentProcessName.isAccessible = true
                packageName = currentProcessName.invoke(null)
            } else {
                val getActivityThread = activityThread.getDeclaredMethod(
                        "currentActivityThread")
                getActivityThread.isAccessible = true
                val getProcessName = activityThread.getDeclaredMethod("getProcessName")
                getProcessName.isAccessible = true
                packageName = getProcessName.invoke(getActivityThread.invoke(null))
            }
            if (packageName is String) {
                return packageName
            }
        } catch (exception: Throwable) {
            Log.d(TAG, "Unable to check ActivityThread for processName", exception)
        }

        // Fallback to the most expensive way
        val pid = Process.myPid()
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (am != null) {
            val processes = am.runningAppProcesses
            if (processes != null && !processes.isEmpty()) {
                for (process in processes) {
                    if (process.pid == pid) {
                        return process.processName
                    }
                }
            }
        }
        return null
    }
}