package tz.co.asoft.ui.samples

import tz.co.asoft.ui.samples.MainComponentViewModel.Intent
import tz.co.asoft.ui.samples.MainComponentViewModel.State
import tz.co.asoft.viewmodel.ViewModel

class MainComponentViewModel : ViewModel<Intent, State>(State.Greet("Hi Juma")) {
    sealed class State {
        class Loading(val msg: String) : State()
        class Greet(val msg: String) : State()
    }

    sealed class Intent {
        class SayHi(val msg: String) : Intent()
    }

    override suspend fun post(i: Intent) = when (i) {
        is Intent.SayHi -> sayHi(i)
    }

    private fun sayHi(i: Intent.SayHi) {
        ui.value = State.Greet(i.msg)
    }
}