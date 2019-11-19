package tz.co.asoft.components.android

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.multidex.MultiDexApplication
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import kotlin.system.exitProcess

class ComponentApplication : MultiDexApplication() {
    val props by lazy { mutableMapOf<String, CProps>() }
    val state by lazy { mutableMapOf<String, CState>() }

    fun reboot() {
        (getSystemService(Context.ALARM_SERVICE) as AlarmManager).apply {
            val i = packageManager.getLaunchIntentForPackage(packageName)?.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            val pi = PendingIntent.getActivity(this@ComponentApplication, 0, i, PendingIntent.FLAG_ONE_SHOT)
            set(AlarmManager.RTC, System.currentTimeMillis() + 1, pi)
        }
        exitProcess(2)
    }
}