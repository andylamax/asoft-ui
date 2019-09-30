package tz.co.asoft.components.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import kotlin.reflect.KClass

inline fun <reified P : CProps, reified S : CState, T : AndroidComponent<P, S>> Parent.child(clazz: KClass<T>, props: P? = null, inFrame: Int = frameId, handler: T.() -> Unit = {}) {
    childFragment(clazz, props, handler).show(inFrame, clazz.java.canonicalName)
}

inline fun <reified P : CProps, reified S : CState, T : AndroidComponent<P, S>> childFragment(clazz: KClass<T>, props: P? = null, handler: T.() -> Unit = {}): T {
    val frag = clazz.java.newInstance() as T
    frag.initProps(props ?: P::class.java.newInstance())
    frag.apply(handler)
    return frag
}

fun FragmentManager.showFragment(id: Int, fragment: Fragment, backStack: String? = null) {
    beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        replace(id, fragment, backStack)
        backStack?.let { addToBackStack(it) }
        commitAllowingStateLoss()
    }
}