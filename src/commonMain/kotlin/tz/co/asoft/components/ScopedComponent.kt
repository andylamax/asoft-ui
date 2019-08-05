package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

expect abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    constructor()
    constructor(props: P)

    protected fun syncState(context: CoroutineContext = coroutineContext, buildState: suspend S.() -> Unit)
}