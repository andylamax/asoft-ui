package tz.co.asoft.components

import tz.co.asoft.platform.Ctx

expect abstract class Component<P : CProps, S : CState> {
    constructor()
    constructor(props: P)

    val ctx: Ctx

    open fun alert(msg: Any?)

    open fun onReady()
    open fun onStop()
    protected fun setState(builder: S.() -> Unit)
    open fun onDone()
}