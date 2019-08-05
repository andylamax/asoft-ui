package tz.co.asoft.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.*
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import tz.co.asoft.ui.lifecycle.ResultData
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass
import tz.co.asoft.ui.ComponentActivity.State as State

abstract class ComponentActivity<S : State> : AppCompatActivity() {

    protected lateinit var state: S

    open class State : AState() {
        var resultData: ResultData? = null
    }

    open val layoutId = R.layout.fragment_frame
    open val frameId = R.id.frame

    val children = mutableMapOf<KClass<*>, ComponentFragment<*, *>>()

    protected inline fun setState(builder: S.() -> Unit) {
        state.apply(builder)
        render()
    }

    override fun onStart() {
        children.clear()
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let {
            state = allStates[this::class] as? S ?: restoreStateFrom(it)
        }
    }

    private fun restoreStateFrom(bundle: Bundle): S {
        val stateClass = bundle.classLoader.loadClass(bundle.getString("state_class")) as Class<S>
        val stateJson = bundle.getString("state_json")
        return gson.fromJson(stateJson, stateClass)
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        allFragments.clear()
        render()
    }

    open fun render(): Any = Unit

    fun Fragment.showFragment(id: Int, backStack: String? = null) = showFragment(id, this, backStack)
    fun showFragment(id: Int, fragment: Fragment, backStack: String? = null) = supportFragmentManager.showFragment(id, fragment, backStack)
    fun Fragment.show(id: Int = frameId, backStack: String? = null) = showFragment(id, this, backStack)

    inline fun <reified P : CProps, reified S : CState, T : ComponentFragment<P, S>> childFragment(clazz: KClass<T>, p: P? = null, handler: T.() -> Unit = {}): T {
        val frag = allFragments.getOrPut(clazz) { clazz.java.newInstance() } as T

        val props = allProps[clazz] as? P ?: p ?: P::class.java.newPropsInstance()
        frag.initProps(props)
        frag.apply(handler)
        val state = allStates[clazz] as? S ?: S::class.java.newStateInstance(P::class.java, props)

        frag.initPropsAndState(props, state)
        allProps[clazz] = props
        allStates[clazz] = state

        children[clazz] = frag
        return frag
    }

    inline fun <reified P : CProps, reified S : CState, T : ComponentFragment<P, S>> child(clazz: KClass<T>, p: P? = null, inFrame: Int = frameId, handler: T.() -> Unit = {}) {
        childFragment(clazz, p, handler).show(inFrame, clazz.java.canonicalName)
    }

    private fun onBackPressed(fm: FragmentManager?): Boolean {
        if (fm != null) {
            if (fm.backStackEntryCount > 0) {
                fm.popBackStack()
                return true
            }

            fm.fragments.forEach {
                if (it?.isVisible == true) {
                    return onBackPressed(it.childFragmentManager)
                }
            }
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        allStates[this::class] = state
        outState?.apply {
            putString("state_class", state::class.java.name)
            putString("state_json", gson.toJson(state))
        }
        super.onSaveInstanceState(outState)
    }

    fun startComponentForResult(intent: Intent, requestCode: Int) = startActivityForResult(intent, requestCode)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = ResultData(requestCode, resultCode, data) { setState { resultData = null } }
        setState { resultData = result }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (onBackPressed(fm)) {
            return
        }
        super.onBackPressed()
    }
}

abstract class ScopedActivity<S : State> : ComponentActivity<S>(), CoroutineScope {
    protected var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected fun syncState(coroutineContext: CoroutineContext = job, builder: suspend S.() -> Unit) {
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