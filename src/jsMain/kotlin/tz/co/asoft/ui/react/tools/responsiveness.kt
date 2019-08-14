package tz.co.asoft.ui.react.tools

import kotlinx.css.CSSBuilder
import kotlin.browser.window

fun CSSBuilder.onMobile(builder: CSSBuilder.() -> Unit) = media("only screen and (orientation: portrait)") {
    +builder
}

fun CSSBuilder.onDesktop(builder: CSSBuilder.() -> Unit) = media("only screen and (orientation: landscape)") {
    +builder
}

fun CSSBuilder.onPaper(builder: CSSBuilder.() -> Unit) = media("print") {
    +builder
}

val isDesktop get() = window.screen.availWidth > window.screen.availHeight

val isMobile get() = isDesktop.not()