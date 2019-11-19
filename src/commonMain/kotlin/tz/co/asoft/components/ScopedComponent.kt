package tz.co.asoft.components

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import tz.co.asoft.rx.lifecycle.ILifeCycle
import tz.co.asoft.rx.lifecycle.LifeCycle
import tz.co.asoft.rx.lifecycle.LiveData
import tz.co.asoft.rx.lifecycle.Observer
import kotlin.coroutines.CoroutineContext

expect abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope, ILifeCycle {
    constructor()
    constructor(props: P)

    protected val lifeCycle: ILifeCycle
    protected val job: CompletableJob
    override val coroutineContext: CoroutineContext

    fun <T> LiveData<T>.observe(onChange: (T) -> Unit): Observer<T>

    fun <T> LiveData<T>.bind()

    protected fun syncState(context: CoroutineContext = coroutineContext, buildState: suspend S.() -> Unit)
}