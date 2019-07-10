package tz.co.asoft.ui.module

import kotlinext.js.jsObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import react.RComponent
import react.RProps
import react.RState
import react.setState
import tz.co.asoft.rx.subscriber.Subscriber
import kotlin.coroutines.CoroutineContext

abstract class ScopedRComponent<P : RProps, S : RState> : RComponent<P, S>, CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Unconfined

    constructor() : super() {
        state = jsObject { init() }
    }

    constructor(props: P) : super(props) {
        state = jsObject { init(props) }
    }

    fun RComponent<P, S>.syncState(block: suspend S.() -> Unit) {
        launch {
            try {
                state.block()
                setState {}
            } catch (err: Throwable) {
                console.log(err.message)
            }
        }
    }

    override fun componentWillUnmount() {
        job.cancel()
    }
}

abstract class ObservingRComponent<T, P : RProps, S : RState> : ScopedRComponent<P, S> {
    var subscriber: Subscriber<T> = Subscriber()

    constructor() : super() {
        state = jsObject { init() }
    }

    constructor(props: P) : super(props) {
        state = jsObject { init(props) }
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        subscriber.cancel()
    }
}

abstract class ObservingComponent<T, P : RProps, S : RState> : ObservingRComponent<MutableList<T>, P, S> {
    constructor() : super() {
        state = jsObject { init() }
    }

    constructor(props: P) : super(props) {
        state = jsObject { init(props) }
    }
}