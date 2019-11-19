package tz.co.asoft.components.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import tz.co.asoft.components.ScopedComponent
import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.rx.lifecycle.ILifeCycle
import tz.co.asoft.rx.lifecycle.LiveData

open class SemiComponent<P : CProps, S : CState> {

    open val layoutId: Int = 0

    var view: View? = null

    protected lateinit var props: P

    var parent: ScopedComponent<*, *>? = null

    protected lateinit var state: S

    val attrs get() = props

    fun initProps(p: P) {
        props = p
    }

    fun attrs(builder: P.() -> Unit) = props.apply(builder)

    fun onCreateView(ctx: Ctx, parent: ViewGroup): View {
        return LayoutInflater.from(ctx).inflate(layoutId, parent)
    }

    open fun onReady(ctx: Ctx) {

    }

    fun <T> LiveData<T>.bind() = parent?.let { observe(it) { executeRender() } }

    protected open fun setState(builder: S.() -> Unit) {
        state.apply(builder)
        executeRender()
    }

    protected fun executeRender() = parent?.launch(Dispatchers.Main) {
        render()
    }

    open fun render(): Any = Unit
}