package tz.co.asoft.ui.samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tornadofx.label
import tornadofx.replaceChildren
import tz.co.asoft.component.Component
import tz.co.asoft.ui.samples.Counter.State

class Counter : Component<Any, State>() {
    class State {
        var count = 0
    }

    override fun onReady() {
        super.onReady()
        start()
    }
    
    private fun start() = launch {
        repeat(10) {
            delay(1000)
            setState { count++ }
        }
    }

    override fun render() {
        root.replaceChildren { label("Count: ${state.count}") }
    }
}