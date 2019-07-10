package tz.co.asoft.ui

import androidx.fragment.app.Fragment

abstract class Singleton<out T : Fragment>(private val builder: () -> T, private val caller: T.() -> Unit = {}) {
    private var instance: T? = null
    open fun getFragment() = (instance ?: builder().also { instance = it }).apply(caller)
}