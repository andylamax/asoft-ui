@file:JsModule("electron")

package tz.co.asoft.ui.samples.electron

external interface ElectronApp {
    fun on(event: String, handler: () -> Unit)
}

external val app: ElectronApp

external interface Config {
    var width: Int
    var height: Int
}

external class BrowserWindow constructor(config: Config) {
    fun loadURL(url: String)
}