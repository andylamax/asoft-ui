package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() : super()
    actual constructor(props: P) : super(props)

    protected val job get() = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.buildState()
            setState { }
        }
    }

    override fun componentWillUnmount() {
        job.cancel()
        super.componentWillUnmount()
    }
}