package tz.co.asoft.ui.samples.jfx

import tornadofx.*

fun main() {
    launch<MyApp>()
}

class MyApp : App(MainActivity::class)

//class MainView : View() {
//    override val root = vbox {
//        label("Hello Lamax")
//    }
//}