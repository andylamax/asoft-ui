package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tz.co.asoft.rx.lifecycle.LifeCycle
import tz.co.asoft.rx.lifecycle.LiveData
import kotlin.coroutines.CoroutineContext

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() : super()
    actual constructor(props: P) : super(props)

    protected actual val lifeCycle = LifeCycle()
    protected actual val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.buildState()
            setState { }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        lifeCycle.start()
    }

    actual fun <T> LiveData<T>.observe(onChange: (T) -> Unit) = observe(lifeCycle, onChange)

    override fun componentWillUnmount() {
        job.cancel()
        lifeCycle.finish()
        super.componentWillUnmount()
    }
}