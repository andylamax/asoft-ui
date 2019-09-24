package tz.co.asoft.components

import react.RBuilder
import react.RComponent
import react.setState
import tz.co.asoft.platform.Ctx
import tz.co.asoft.ui.action.Action
import tz.or.self.ui.components.Error
import tz.co.asoft.ui.react.composites.async.Loading
import kotlin.browser.window

actual abstract class Component<P : CProps, S : CState> : RComponent<P, S> {

    actual constructor() : super()
    actual constructor(props: P) : super(props)

    actual val ctx = object : Ctx() {}

    actual open fun onReady() {}

    actual open fun alert(msg: Any?) = window.alert(msg.toString())

    override fun componentDidMount() {
        onReady()
    }

    actual open fun onStop() {}

    protected actual fun setState(builder: S.() -> Unit) {
        setState(buildState = builder)
    }

    actual open fun onDone() {}

    override fun componentWillUnmount() {
        onDone()
    }

    fun RBuilder.showLoading(msg: String) = child(Loading::class.js, Loading.Props()) {
        attrs.msg = msg
        val p = props
        if (p is ModuleProps) attrs { theme = p.theme }
    }

    fun RBuilder.showError(msg: String, actions: List<Action> = listOf()) = child(Error::class.js, Error.Props()) {
        attrs.msg = msg
        attrs.actions = actions
        val p = props
        if (p is ModuleProps) attrs { theme = p.theme }
    }
}