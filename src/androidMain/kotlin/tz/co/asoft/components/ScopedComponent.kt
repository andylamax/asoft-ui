package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
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
    actual val job = Job()
    actual override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            state.apply { buildState() }
            executeRender()
        }
    }

    actual fun <T> LiveData<T>.observe(onChange: (T) -> Unit) = observe(lifeCycle, onChange)

    actual fun <T> LiveData<T>.bind() {
        observe { executeRender() }
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