package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import tz.co.asoft.rx.lifecycle.LifeCycle
import tz.co.asoft.rx.lifecycle.LiveData

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() {

    }

    actual constructor(props: P) {

    }

    protected actual val lifeCycle = LifeCycle()
    protected actual val job: Job = Job()

    actual fun <T> LiveData<T>.observe(onChange: (T) -> Unit) = observe(lifeCycle, onChange)

    protected actual fun syncState(
            context: kotlin.coroutines.CoroutineContext,
            buildState: suspend S.() -> Unit
    ) {
    }
}