package tz.co.asoft.ui.samples

import android.view.View
import android.widget.TextView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tz.co.asoft.component.Component
import tz.co.asoft.ui.ViewHolder

class Counter : Component<Any, Counter.State>() {
    override val layoutId = R.layout.view_counter

    class State {
        var count = 0
    }

    class VH(v: View?) : ViewHolder(v) {
        val tv: TextView by Id(R.id.counter_text)
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
        VH(view).bindUI()
    }

    private fun VH.bindUI() {
        tv.text = "Count: ${state.count}"
    }
}