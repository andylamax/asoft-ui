package tz.co.asoft.components.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import tz.co.asoft.ui.R
import kotlin.reflect.KClass

abstract class AndroidComponent<P : CProps, S : CState> : Fragment(), Parent {

    protected lateinit var props: P
    protected lateinit var state: S

    open val layoutId = R.layout.fragment_frame
    override val frameId = R.id.frame

    override val fManager get() = childFragmentManager

    fun initProps(p: P) {
        props = p
        onReceiveProps(p)
    }

    open fun onReceiveProps(props: P) {

    }

    val attrs get() = props

    fun attrs(builder: P.() -> Unit) = props.apply(builder)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
        render()
    }

    protected open fun setState(builder: S.() -> Unit) {
        state.apply(builder)
        render()
    }

    open fun render(): Any = Unit
}
