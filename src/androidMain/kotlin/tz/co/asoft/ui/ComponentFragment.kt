package tz.co.asoft.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class ComponentFragment<P : Any, S : Any> : Fragment() {
    protected lateinit var state: S
    protected lateinit var props: P

    val attrs get() = props

    abstract val layoutId: Int

    protected fun ComponentFragment<P, S>.setState(builder: S.() -> Unit) {
        state.apply(builder)
        render()
    }

    fun ComponentFragment<P, S>.attrs(builder: P.() -> Unit) = props.apply(builder)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
        render()
    }

    protected fun Fragment.showFragment(id: Int, backStack: String? = null) = showFragment(id, this, backStack)
    protected fun showFragment(id: Int, fragment: Fragment, backStack: String? = null) = childFragmentManager.showFragment(id, fragment, backStack)

    open fun render(): Any {
        return Unit
    }
}

abstract class ScopedFragment<P : Any, S : Any> : ComponentFragment<P, S>(), CoroutineScope {
    protected var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected fun ScopedFragment<P, S>.syncState(coroutineContext: CoroutineContext = job, builder: suspend S.() -> Unit) {
        launch(coroutineContext) {
            state.apply {
                builder()
            }
            withContext(Dispatchers.Main) { setState { } }
        }
    }

    override fun onResume() {
        super.onResume()
        job = Job()
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}

fun FragmentManager.showFragment(id: Int, fragment: Fragment, backStack: String? = null) {
    beginTransaction().apply {
        replace(id, fragment, backStack)
        backStack?.let { addToBackStack(it) }
        commitAllowingStateLoss()
    }
}