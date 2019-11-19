package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tz.co.asoft.rx.lifecycle.ILifeCycle
import tz.co.asoft.rx.lifecycle.LifeCycle
import tz.co.asoft.rx.lifecycle.LiveData
import tz.co.asoft.rx.lifecycle.Observer
import kotlin.coroutines.CoroutineContext

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope, ILifeCycle {
    actual constructor() : super()
    actual constructor(props: P) : super(props)

    protected actual val lifeCycle: ILifeCycle get() = this
    protected actual val job = Job()

    actual override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.buildState()
            setState { }
        }
    }

    actual fun <T> LiveData<T>.observe(onChange: (T) -> Unit) = observe(lifeCycle, onChange)

    actual fun <T> LiveData<T>.bind() {
        observe { setState { } }
    }

    override var lifeState = ILifeCycle.LifeState.CREATED
    override val observers by lazy { mutableSetOf<Observer<*>>() }

    override fun onReady() {
        super.onReady()
        start()
    }

    override fun onResumed() {
        super.onResumed()
        resume()
    }

    override fun onPaused() {
        pause()
        super.onPaused()
    }

    override fun onFinished() {
        finish()
        job.complete()
        super.onFinished()
    }
}