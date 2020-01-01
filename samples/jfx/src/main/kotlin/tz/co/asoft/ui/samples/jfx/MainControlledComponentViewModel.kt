package tz.co.asoft.ui.samples.jfx

import tz.co.asoft.ui.samples.jfx.MainControlledComponentViewModel.Intent
import tz.co.asoft.ui.samples.jfx.MainControlledComponentViewModel.State
import tz.co.asoft.viewmodel.ViewModel

class MainControlledComponentViewModel : ViewModel<Intent, State>(State.Counting(0)) {

    sealed class State {
        data class Counting(val value: Int) : State()
    }

    sealed class Intent {
        object Increment : Intent()
    }

    override suspend fun post(i: Intent) {
        val state = ui.value as State.Counting
        ui.value = state.copy(value = state.value + 1)
    }
}