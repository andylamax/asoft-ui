package tz.co.asoft.ui.samples.node

import kotlinext.js.jsObject
import tz.co.asoft.ui.samples.path.join
import tz.co.asoft.ui.samples.url.Url
import tz.co.asoft.ui.samples.url.format

external val __dirname: String

const val resourcesDir = "/media/andylamax/63C23C360914D355/Projects/asoft-ui/samples/electron/build/processedResources/Js/main"

fun fileResources(name: String) = format(jsObject<Url> {
    pathname = join(resourcesDir, name)
    protocol = "file:"
    slashes = true
})