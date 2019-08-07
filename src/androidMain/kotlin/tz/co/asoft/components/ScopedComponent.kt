package tz.co.asoft.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tz.co.asoft.ui.gson
import kotlin.coroutines.CoroutineContext

actual abstract class ScopedComponent<P : CProps, S : CState> : Component<P, S>, CoroutineScope {
    actual constructor() : super()
    actual constructor(props: P) : super(props)

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected actual fun syncState(context: CoroutineContext, buildState: suspend S.() -> Unit) {
        launch(context) {
            if (stateIsAltered(buildState)) {
                render()
            }
        }
    }

    private suspend fun stateIsAltered(buildState: suspend S.() -> Unit): Boolean {
        val preState = gson.toJson(state)
        state.apply { buildState() }
        val postState = gson.toJson(state)
        return preState != postState
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}