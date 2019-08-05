package tz.co.asoft.components

import tz.co.asoft.ui.ComponentFragment

actual abstract class Component<P : CProps, S : CState> actual constructor() : ComponentFragment<P, S>() {

    actual val ctx get() : Any = activity!!.applicationContext

    actual constructor(props: P) : this() {
        this.props = props
    }

    actual open fun onReady() {}

    override fun onResume() {
        super.onResume()
        onReady()
    }

    actual final override fun setState(builder: S.() -> Unit) {
        super.setState(buildState = builder)
    }

    actual open fun onDone() {}

    override fun onDestroy() {
        onDone()
        super.onDestroy()
    }
}