package tz.co.asoft.ui.samples.web

import kotlinext.js.jsObject
import kotlinx.coroutines.*
import react.RProps
import react.dom.render
import react.functionalComponent
import tz.co.asoft.ui.samples.Counter
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(app, jsObject<RProps> { }) {}
        }
        start()
    }
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