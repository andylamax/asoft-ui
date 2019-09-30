package tz.co.asoft.components

import android.os.Bundle
import android.view.View
import tz.co.asoft.components.android.AndroidComponent
import tz.co.asoft.components.android.child
import tz.co.asoft.ui.action.Action
import tz.co.asoft.ui.alert

actual abstract class Component<P : CProps, S : CState> actual constructor() : AndroidComponent<P, S>() {

    actual val ctx get() = activity!!.applicationContext!!

    actual constructor(props: P) : this() {
        this.props = props
    }

    actual open fun alert(msg: Any?) {
        ctx.alert(msg)
    }

    actual open fun onReady() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReady()
    }

    actual override fun onStop() {
        super.onStop()
    }

    actual final override fun setState(builder: S.() -> Unit) {
        super.setState(builder)
    }

    actual open fun onDone() {}

    override fun onDestroy() {
        onDone()
        super.onDestroy()
    }

    open fun showLoading(msg: String) = child(Loading::class, Loading.Props()) {
        attrs.msg = msg
        attrs.theme = props.theme
    }

    open fun showError(msg: String, actions: List<Action> = listOf()) = child(Error::class, Error.Props()) {
        attrs.msg = msg
        attrs.actions = actions
    }
}