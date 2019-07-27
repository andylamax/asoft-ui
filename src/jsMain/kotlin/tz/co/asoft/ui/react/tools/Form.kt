package tz.co.asoft.ui.react.tools

import org.w3c.dom.HTMLFormElement

operator fun HTMLFormElement.get(name: String): String? {
    val node = asDynamic()[name]
    return if (node == undefined) {
        null
    } else {
        if (node.value == undefined) {
            null
        } else {
            node.value.unsafeCast<String>()
        }
    }
}