package tz.co.asoft.component

import android.app.Activity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import tz.co.asoft.LifeCycleMethods
import tz.co.asoft.components.android.AndroidComponent
import tz.co.asoft.components.android.child
import tz.co.asoft.rx.LiveData
import tz.co.asoft.ui.action.Action
import kotlin.coroutines.CoroutineContext

abstract class Component<P : Any, S : Any> : AndroidComponent<P, S>(), CoroutineScope, LifeCycleMethods {
    protected val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val application get() = activity.application

    val activity: Activity get() = requireActivity()

    val ctx get() = activity.applicationContext!!

    override fun executeRender() {
        launch(Dispatchers.Main) {
            render()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onReady()
    }

    override fun onResume() {
        super<AndroidComponent>.onResume()
    }

    open fun showLoading(message: String) = child(Loading::class.java, Loading.Props()) {
        attrs { msg = message }
    }

    open fun showError(message: String, actions: List<Action> = listOf()) = child(Error::class.java, Error.Props()) {
        attrs { msg = message }
    }

    fun <T> LiveData<T>.observe(action: (T) -> Unit) = onChange(this@Component, action)

    override fun onStop() {
        super<AndroidComponent>.onStop()
    }

    override fun onDestroy() {
        job.cancel()
        super<AndroidComponent>.onDestroy()
    }
}