package tz.co.asoft.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.*
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import tz.co.asoft.ui.lifecycle.ResultData
import tz.co.asoft.ui.tools.SyncStorage
import tz.co.asoft.ui.tools.fromJson
import tz.co.asoft.ui.tools.toJson
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass
import tz.co.asoft.ui.ComponentActivity.State as State

abstract class ComponentActivity<S : State> : AppCompatActivity() {

    protected lateinit var state: S

    private val className by lazy { this::class.java.name }
    private val stateClassName by lazy { state::class.java.name }
    private val stateClass by lazy { Class.forName(stateClassName) as Class<S> }

    private val storage by lazy {
        SyncStorage(applicationContext, application.packageName)
    }

    open class State : AState() {
        var resultData: ResultData? = null
    }

    open val layoutId = R.layout.fragment_frame
    open val frameId = R.id.frame

    protected fun setState(builder: S.() -> Unit) {
        if (stateIsAltered(builder)) {
            render()
        }
    }

    protected fun stateIsAltered(buildState: S.() -> Unit): Boolean {
        val preState = gson.toJson(state)
        state.apply(buildState)
        val postState = gson.toJson(state)
        return preState != postState
    }

    override fun onStart() {
        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        restoreState()
        render()
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        restoreState()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreState()
    }

    private fun restoreState() {
        state = storage.restoreState() ?: stateClass.newStateInstance<AProps, S>()
    }

    private fun SyncStorage.restoreState(): S? = get(stateClassName)?.fromJson(stateClass)

    open fun render(): Any = Unit

    fun Fragment.showFragment(id: Int, backStack: String? = null) = showFragment(id, this, backStack)
    fun showFragment(id: Int, fragment: Fragment, backStack: String? = null) = supportFragmentManager.showFragment(id, fragment, backStack)
    fun Fragment.show(id: Int = frameId, backStack: String? = null) = showFragment(id, this, backStack)

    inline fun <reified P : CProps, reified S : CState, T : ComponentFragment<P, S>> childFragment(clazz: KClass<T>, p: P? = null, handler: T.() -> Unit = {}): T {
        val frag = clazz.java.newInstance() as T
        val props = p ?: P::class.java.newPropsInstance()
        frag.initProps(props)
        frag.apply(handler)
        val state = S::class.java.newStateInstance(P::class.java, props)

        frag.initPropsAndState(props, state)
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

    private fun Bundle.saveState() = putString("state_json", state.toJson())

    private fun SyncStorage.saveState() = set(stateClassName, state.toJson())

    override fun onSaveInstanceState(outState: Bundle?) {
        storage.saveState()
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
            if (stateIsAltered(builder)) {
                withContext(Dispatchers.Main) { render() }
            }
        }
    }

    protected suspend fun stateIsAltered(buildState: suspend S.() -> Unit): Boolean {
        val preState = gson.toJson(state)
        state.apply { buildState() }
        val postState = gson.toJson(state)
        return preState != postState
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