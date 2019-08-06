package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() {

    }

    actual constructor(props: P) {

    }

    protected actual fun syncState(
        context: kotlin.coroutines.CoroutineContext,
        buildState: suspend S.() -> Unit
    ) {
    }
}