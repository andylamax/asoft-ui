package tz.co.asoft.ui.samples.jfx

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tornadofx.label
import tornadofx.replaceChildren
import tz.co.asoft.component.ControlledComponent
import tz.co.asoft.ui.samples.jfx.MainControlledComponentViewModel.Intent
import tz.co.asoft.ui.samples.jfx.MainControlledComponentViewModel.State
import tz.co.asoft.ui.samples.jfx.di.injection

class MainControlledComponent : ControlledComponent<Any, Intent, State, MainControlledComponentViewModel>() {

    override val viewModel by lazy { injection.viewModel.mainControlledComponent() }

    init {
        start()
    }

    private fun start() {
        println("Main Component docked")
        launch {
            repeat(2000) {
                delay(1)
                post(Intent.Increment)
            }
        }
    }

    override fun render(ui: State) {
        when (ui) {
            is State.Counting -> root.replaceChildren {
                label("Counts: ${ui.value}") {
                    minWidth = 100.0
                }
            }
        }
    }
}