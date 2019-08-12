package tz.co.asoft.components

expect abstract class Component<P : CProps, S : CState> {
    constructor()
    constructor(props: P)
    val ctx: Any
    open fun onReady()
    open fun onStop()
    protected fun setState(builder: S.() -> Unit)
    open fun onDone()
}