package tz.co.asoft.ui.samples.jfx

import javafx.scene.layout.HBox
import tornadofx.hbox
import tz.co.asoft.component.ComponentActivity
import tz.co.asoft.component.child
import tz.co.asoft.ui.samples.Counter

class MainActivity : ComponentActivity() {
    lateinit var leftBox: HBox
    lateinit var rightBox: HBox

    override val root = hbox {
        leftBox = hbox {
            minWidth = 100.0
        }

        rightBox = hbox {
            minWidth = 100.0
        }
    }

    private fun showMainComponentLeft() = child(Counter::class.java, Any(), inNode = leftBox)
    private fun showMainComponentRight() = child(MainControlledComponent::class.java, Any(), inNode = rightBox)
    override fun render() {
        showMainComponentLeft()
        showMainComponentRight()
    }
}