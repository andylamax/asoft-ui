@file:JsModule("url")

package tz.co.asoft.ui.samples.url

import org.w3c.dom.url.URL

external class Url(url: String, base: String = definedExternally) : URL {
    var slashes: Boolean
}

external fun format(url: String): String

external fun format(url: Url): String