package tz.co.asoft.ui.samples.jfx

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tornadofx.label
import tornadofx.replaceChildren
import tz.co.asoft.component.Component
import tz.co.asoft.ui.samples.jfx.MainComponent.State

class MainComponent : Component<Any, State>() {
    class State {
        var counts = 0
    }

    init {
        start()
    }

    private fun start() {
        println("Main Component docked")
        launch {
            repeat(10) {
                delay(1000)
                setState { counts++ }
            }
        }
    }

    override fun render() = root.replaceChildren {
        label("${state.counts}")
    }.also { println("Counts: ${state.counts}") }
}