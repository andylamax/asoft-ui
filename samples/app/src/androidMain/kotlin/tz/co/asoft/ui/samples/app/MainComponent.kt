package tz.co.asoft.ui.samples.app

import android.os.Bundle
import tz.co.asoft.component.ControlledComponent
import tz.co.asoft.components.android.child
import tz.co.asoft.ui.samples.MainComponentViewModel
import tz.co.asoft.ui.samples.MainComponentViewModel.Intent
import tz.co.asoft.ui.samples.MainComponentViewModel.State
import tz.co.asoft.ui.samples.di.injection

class MainComponent : ControlledComponent<Any, Intent, State, MainComponentViewModel>(), Splash.Handler {
    override val viewModel by lazy { injection.viewModel.mainComponent(requireActivity()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        post(Intent.SayHi("Hello Lamax"))
    }

    private fun showSplash(msg: String) = child(Splash::class.java, Splash.Props()) {
        attrs { text = msg }
    }

    override fun onSplashTextChanged(txt: String) {
        post(Intent.SayHi("Hello $txt"))
    }

    override fun render(ui: State) {
        when (ui) {
            is State.Loading -> showLoading(ui.msg)
            is State.Greet -> showSplash(ui.msg)
        }
    }
}