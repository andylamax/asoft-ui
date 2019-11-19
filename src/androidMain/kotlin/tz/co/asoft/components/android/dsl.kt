package tz.co.asoft.components.android

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import tz.co.asoft.components.ScopedComponent
import kotlin.reflect.KClass

inline fun <reified P : CProps, T : AndroidComponent<P, *>> Parent.child(clazz: KClass<T>, props: P? = null, inFrame: Int = frameId, noinline handler: T.() -> Unit = {}) {
    childFragment(clazz, props, handler).show(inFrame, clazz.java.canonicalName)
}

inline fun <reified P : CProps, T : AndroidComponent<P, *>> Parent.childFragment(clazz: KClass<T>, props: P? = null, noinline handler: T.() -> Unit = {}): T {
    val frag = clazz.java.newInstance() as T
    val p = props ?: P::class.java.newInstance()
    frag.initProps(p)
    frag.apply(handler)
    val app: ComponentApplication? = when (this) {
        is Fragment -> this.activity?.application as? ComponentApplication
        is ComponentActivity -> this.application as? ComponentApplication
        else -> null
    }
    val propsKey = "${this::class.java.simpleName}-${frag::class.java.name}-${p::class.java.name}"
    frag.arguments = Bundle().apply {
        putString("props", propsKey)
    }
    app?.props?.set(propsKey, frag.attrs)
//    if (app?.props?.containsKey(propsKey) == false) {
//        app.props[propsKey] = frag.attrs
//    }
    return frag
}

inline fun <reified P : CProps, T : SemiComponent<P, *>> ScopedComponent<*, *>.child(clazz: KClass<T>, props: P? = null, noinline handler: T.() -> Unit = {}): T {
    val frag = clazz.java.newInstance() as T
    val p = props ?: P::class.java.newInstance()
    frag.initProps(p)
    frag.apply(handler)
    frag.parent = this
    val app = activity?.application as? ComponentApplication

    val propsKey = "${this::class.java.simpleName}-${frag::class.java.name}-${p::class.java.name}"
    if (app?.props?.containsKey(propsKey) == false) {
        app.props[propsKey] = frag.attrs
    }

    fManager.popBackStackImmediate()
    view?.findViewById<FrameLayout>(frameId)?.apply {
        removeAllViews()
        frag.view = frag.onCreateView(ctx, this)
        addView(frag.view)
    }

    return frag
}

fun FragmentManager.showFragment(id: Int, fragment: Fragment, backStack: String? = null) {
    beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        val fragName: String? = backStack
        replace(id, fragment, null)
        fragName?.let { addToBackStack(it) }
        commitAllowingStateLoss()
    }
}