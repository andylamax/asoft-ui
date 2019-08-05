package tz.co.asoft.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

val allFragments = mutableMapOf<KClass<*>, Fragment>()
val allStates = mutableMapOf<KClass<*>, Any>()
val allProps = mutableMapOf<KClass<*>, Any>()

internal val gson = GsonBuilder().apply {
    setLenient()
    val strategy = object : ExclusionStrategy {
        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            return clazz?.name?.contains("kotlin.jvm.functions") ?: false
        }

        override fun shouldSkipField(f: FieldAttributes?) = false
    }
    addDeserializationExclusionStrategy(strategy)
    addSerializationExclusionStrategy(strategy)
}.create()

abstract class ComponentFragment<P : CProps, S : CState> : Fragment() {
    protected lateinit var state: S
    protected lateinit var props: P

    val attrs get() = props

    open val layoutId = R.layout.fragment_frame
    open val frameId = R.id.frame

    val children = mutableMapOf<KClass<*>, ComponentFragment<*, *>>()

    fun initPropsAndState(p: P?, s: S) {
        initProps(p)
        initState(s)
    }

    fun initProps(p: P?) {
        p?.let { props = it }
    }

    fun initState(s: S) {
        state = s
    }

    protected open fun setState(buildState: S.() -> Unit) {
        state.apply(buildState)
        render()
    }

    fun attrs(builder: P.() -> Unit) = props.apply(builder)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        savedInstanceState?.let { restorePropsAndStateFrom(it) }
        return inflater.inflate(layoutId, container, false)
    }

    private fun restorePropsAndStateFrom(bundle: Bundle) {
        props = (allProps[this::class] as? P) ?: restorePropsFrom(bundle)
        state = (allStates[this::class] as? S) ?: restoreStateFrom(bundle)
    }

    private fun restorePropsFrom(bundle: Bundle): P {
        val propsClass = bundle.classLoader.loadClass(bundle.getString("props_class")) as Class<P>
        val propsJson = bundle.getString("props_json")
        return try {
            gson.fromJson(propsJson, propsClass)
        } catch (c: Throwable) {
            propsClass.newPropsInstance()
        }
    }

    private fun restoreStateFrom(bundle: Bundle): S {
        val stateClass = bundle.classLoader.loadClass(bundle.getString("state_class")) as Class<S>
        val stateJson = bundle.getString("state_json")
        return try {
            gson.fromJson(stateJson, stateClass)
        } catch (c: Throwable) {
            stateClass.newStateInstance<P, S>()
        }
    }

    fun alert(msg: Any?): Job? = context?.alert(msg)

    override fun onResume() {
        super.onResume()
        render()
    }

    fun startComponentForResult(intent: Intent, requestCode: Int) {
        (activity as? ComponentActivity<*>)?.startComponentForResult(intent, requestCode)
    }

    fun showFragment(id: Int, fragment: Fragment, backStack: String? = null) = childFragmentManager.showFragment(id, fragment, backStack)
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

    override fun onPause() {
        savePropsAndStateToMemory()
        super.onPause()
    }

    private fun savePropsAndStateToMemory() {
        allStates[this::class] = state
        allProps[this::class] = props
    }

    override fun onSaveInstanceState(outState: Bundle) {
        savePropsAndStateToMemory()
        outState.apply {
            try {
                putString("state_json", gson.toJson(state))
                putString("props_json", gson.toJson(props))
                putString("props_class", props::class.java.name)
                putString("state_class", state::class.java.name)
            } catch (c: Throwable) {
            }
        }
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