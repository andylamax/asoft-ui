package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tz.co.asoft.rx.lifecycle.LifeCycle
import tz.co.asoft.rx.lifecycle.LiveData
import tz.co.asoft.ui.gson
import kotlin.coroutines.CoroutineContext

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() : super()
    actual constructor(props: P) : super(props)

    protected actual val lifeCycle = LifeCycle()
    protected actual val job: Job = Job()
    actual override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.apply { buildState() }
            render()
        }
    }

    override fun onStart() {
        super.onStart()
        lifeCycle.start()
    }

    actual fun <T> LiveData<T>.observe(onChange: (T) -> Unit) = observe(lifeCycle, onChange)

    override fun onStop() {
        lifeCycle.stop()
        super.onStop()
    }

    override fun onDestroy() {
        job.cancel()
        lifeCycle.finish()
        super.onDestroy()
    }
}