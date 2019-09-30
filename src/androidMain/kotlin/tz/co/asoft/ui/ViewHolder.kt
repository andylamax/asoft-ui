package tz.co.asoft.ui

import android.view.View
import kotlin.reflect.KProperty

open class ViewHolder(protected val view: View?) {

    fun viewId(id: Int) = Delegate(id)

    fun Id(id:Int) = viewId(id)

    inner class Delegate(private val id: Int) {
        operator fun <T:View> getValue(thisRef: Any?, prop: KProperty<*>): T {
            return view!!.findViewById(id)
        }
    }
}