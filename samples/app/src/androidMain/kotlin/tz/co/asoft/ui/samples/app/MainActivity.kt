package tz.co.asoft.ui.samples.app

import tz.co.asoft.components.android.ComponentActivity
import tz.co.asoft.components.android.child
import tz.co.asoft.ui.samples.Counter

class MainActivity : ComponentActivity() {
    private fun showMainComponent() = child(Counter::class.java, Any())

    override fun render() = showMainComponent()
}