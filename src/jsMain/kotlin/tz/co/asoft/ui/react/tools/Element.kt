package tz.co.asoft.ui.react.tools

import kotlinx.css.TagSelector
import org.w3c.dom.Element
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement

operator fun HTMLFormElement.get(name: String): String? {
    val node = input(name)
    return if (node == undefined) {
        null
    } else {
        if (node.value == undefined) {
            null
        } else {
            node.value
        }
    }
}

val submit get() = TagSelector("""[type="submit"]""")

fun <E : Element> Element.node(selector: TagSelector) = querySelector(selector.tagName).unsafeCast<E?>()
fun <E : Element> Element.nodes(selector: TagSelector) = querySelectorAll(selector.tagName).unsafeCast<E?>()
fun <E : Element> Element.nodeNamed(name: String) = querySelector("""[name="$name"]""").unsafeCast<E?>()
fun Element.input(name: String) = nodeNamed<HTMLInputElement>(name)

fun HTMLInputElement.type(text: String) {
    value = text
}