package tz.co.asoft.ui.samples.web

import kotlinext.js.jsObject
import kotlinx.coroutines.*
import react.*
import react.dom.render
import styled.css
import styled.styledDiv
import tz.co.asoft.component.Component
import tz.co.asoft.ui.samples.Counter
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(App::class) {
                child(app, jsObject<RProps> { }) {}
            }
        }
        start()
    }
    console.error("da fuq")
}

fun start() = GlobalScope.launch {
    val dispatchers = listOf(Dispatchers.Default, Dispatchers.Unconfined, Dispatchers.Main)
    repeat(10) {
        delay(1000)
        launch(dispatchers.random()) {
            document.getElementById("heading")?.apply {
                innerHTML = "Time: $it<br>"
                innerHTML += "Coroutine: ${coroutineContext[Job]}"
            }
        }
    }
}

val app = functionalComponent<RProps> {
    child(Counter::class) {}
}

class App : Component<RProps, App.State>() {

    class State : RState {
        var counter = 0

        @OptIn(ExperimentalStdlibApi::class)
        var people = buildList {
            add("John")
            add("Michael")
        }
    }

    init {
        state = State()
    }

    override fun componentDidMount() {
        super.componentDidMount()
        count()
    }

    private fun count(): Job = launch {
        setState { counter++ }
        delay(1000)
        count()
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        +"This is a huge: ${state.counter}"
        styledDiv { props.children() }
    }
}