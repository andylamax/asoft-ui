package tz.co.asoft.components

import react.RComponent
import react.setState

actual abstract class Component<P : CProps, S : CState> : RComponent<P, S> {

    actual constructor() : super()
    actual constructor(props: P) : super(props)

    actual val ctx = Any()

    actual open fun onReady() {}
    override fun componentDidMount() {
        super.componentDidMount()
        onReady()
    }

    protected actual fun setState(builder: S.() -> Unit) {
        setState(buildState = builder)
    }

    actual open fun onDone() {}

    override fun componentWillUnmount() {
        onDone()
        super.componentWillUnmount()
    }
}