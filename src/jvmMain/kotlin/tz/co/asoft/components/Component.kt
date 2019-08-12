package tz.co.asoft.components

actual abstract class Component<P : CProps, S : CState> {
    actual constructor()

    actual constructor(props: P)

    actual val ctx: Any = Unit

    actual open fun onReady() {}

    actual open fun onStop() {}

    protected actual fun setState(builder: S.() -> Unit) {}

    actual open fun onDone() {}
}