package tz.co.asoft.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.*
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import tz.co.asoft.ui.tools.SyncStorage
import tz.co.asoft.ui.tools.fromJson
import tz.co.asoft.ui.tools.toJson
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

abstract class ComponentFragment<P : CProps, S : CState> : Fragment() {
    protected lateinit var state: S
    protected lateinit var props: P

    private val className by lazy { this::class.java.name }
    private val propsClassName by lazy { props::class.java.name }
    private val stateClassName by lazy { state::class.java.name }
    private val propsClass by lazy { Class.forName(propsClassName) as Class<P> }
    private val stateClass by lazy { Class.forName(stateClassName) as Class<S> }

    private val storage by lazy {
        SyncStorage(activity!!.applicationContext, activity!!.application.packageName)
    }

    val attrs get() = props

    open val layoutId = R.layout.fragment_frame
    open val frameId = R.id.frame

    fun initPropsAndState(p: P?, s: S) {
        initProps(p)
        initState(s)
    }

    fun initProps(p: P?) {
        p?.let { props = it }
    }

    private fun initState(s: S) {
        state = s
    }

    protected open fun setState(buildState: S.() -> Unit) {
        if (stateIsAltered(buildState)) {
            render()
        }
    }

    private fun stateIsAltered(buildState: S.() -> Unit): Boolean {
        val preState = gson.toJson(state)
        state.apply(buildState)
        val postState = gson.toJson(state)
        return preState != postState
    }

    fun attrs(builder: P.() -> Unit) = props.apply(builder)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        restoreState()
        return inflater.inflate(layoutId, container, false).also { render() }
    }

    private fun restoreState() {
        state = storage.restoreState() ?: stateClass.newStateInstance(propsClass, props)
    }

    private fun SyncStorage.restoreState(): S? = get(stateClassName)?.fromJson(stateClass)

    fun alert(msg: Any?): Job? = context?.alert(msg)

    fun startComponentForResult(intent: Intent, requestCode: Int) {
        (activity as? ComponentActivity<*>)?.startComponentForResult(intent, requestCode)
    }

    fun showFragment(id: Int, fragment: Fragment, backStack: String? = null) = childFragmentManager.showFragment(id, fragment, backStack)
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


    private fun SyncStorage.saveState() {
        set(propsClassName, props.toJson())
        set(stateClassName, state.toJson())
    }

    private fun log(msg: String) = Log.d("components", msg)
    open fun onSaveInstanceState() {
        storage.saveState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        onSaveInstanceState()
        super.onSaveInstanceState(outState)
    }


    open fun render(): Any {
        return Unit
    }
}

abstract class ScopedFragment<P : AProps, S : AState> : ComponentFragment<P, S>(), CoroutineScope {
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

    private suspend fun stateIsAltered(buildState: suspend S.() -> Unit): Boolean {
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

fun <P : CProps> Class<P>.newPropsInstance() = try {
    newInstance() as P
} catch (c: Throwable) {
    getDeclaredField("INSTANCE").get(null) as P
}

fun <P : CProps, S : CState> Class<S>.newStateInstance(pClass: Class<P>? = null, props: P? = null) = try {
    if (props != null && pClass != null) {
        val ctr = getConstructor(pClass)
        ctr.newInstance(props)!!
    } else {
        throw Throwable("props is null")
    }
} catch (c: Throwable) {
    newInstance()!!
}

fun FragmentManager.showFragment(id: Int, fragment: Fragment, backStack: String? = null) {
    beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        replace(id, fragment, backStack)
        backStack?.let { addToBackStack(it) }
        commit()
    }
}