package tz.co.asoft.components

import kotlinx.coroutines.Job
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx

actual abstract class Component<P : CProps, S : CState> {
    actual constructor()

    actual constructor(props: P)

    protected open val job = Job()

    companion object {
        val globalActivity = Activity()
    }

    actual val activity by lazy { globalActivity }

    actual val ctx: Ctx by lazy { activity }

    actual open fun onReady() {

    }

    actual open fun onResumed() {

    }

    actual open fun onPaused() {

    }

    protected actual fun setState(builder: S.() -> Unit) {}

    actual open fun onFinished() {

    }

    actual open fun alert(msg: Any?) = println(msg)
}