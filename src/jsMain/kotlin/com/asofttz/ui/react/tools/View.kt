package com.asofttz.ui.react.tools

import org.w3c.dom.Element
import kotlin.browser.document

object View {
    fun getId(name: String = "view"): String {
        val id = "$name-id-${(1000000..9999999).random()}"
        return if (byId<Element>(id) == null) {
            id
        } else {
            getId(name)
        }
    }

    fun <T : Element> byId(id: String): T? = document.getElementById(id).unsafeCast<T?>()
}