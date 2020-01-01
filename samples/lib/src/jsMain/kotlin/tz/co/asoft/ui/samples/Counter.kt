package tz.co.asoft.ui.samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.RBuilder
import react.RProps
import react.RState
import react.setState
import styled.styledP
import tz.co.asoft.component.Component
import tz.co.asoft.ui.samples.Counter.State

class Counter : Component<RProps, State>() {
    class State : RState {
        var count = 0
    }

    init {
        state = State()
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

    override fun RBuilder.render() {
        styledP { +"Count: ${state.count}" }
    }
}