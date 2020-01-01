package tz.co.asoft.ui.samples

import kotlinext.js.jsObject
import tz.co.asoft.ui.samples.electron.BrowserWindow
import tz.co.asoft.ui.samples.electron.app
import tz.co.asoft.ui.samples.node.fileResources

fun main() {
    app.on("ready") {
        BrowserWindow(jsObject { width = 800; height = 600 }).apply {
            loadURL(fileResources("index.html"))
        }
    }
}