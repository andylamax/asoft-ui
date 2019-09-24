package tz.co.asoft.components

import tz.co.asoft.platform.Ctx

actual abstract class Component<P : CProps, S : CState> {
    actual constructor()

    actual constructor(props: P)

    actual val ctx = object : Ctx() {}

    actual open fun onReady() {}

    actual open fun onStop() {}

    protected actual fun setState(builder: S.() -> Unit) {}

    actual open fun onDone() {}

    actual open fun alert(msg: Any?) = println(msg)
}