package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import tz.co.asoft.rx.lifecycle.LifeCycle
import tz.co.asoft.rx.lifecycle.LiveData
import tz.co.asoft.rx.lifecycle.Observer
import kotlin.coroutines.CoroutineContext

expect abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    constructor()
    constructor(props: P)

    protected val lifeCycle: LifeCycle
    protected val job: Job
    override val coroutineContext: CoroutineContext

    fun <T> LiveData<T>.observe(onChange: (T) -> Unit): Observer<T>

    protected fun syncState(context: CoroutineContext = coroutineContext, buildState: suspend S.() -> Unit)
}