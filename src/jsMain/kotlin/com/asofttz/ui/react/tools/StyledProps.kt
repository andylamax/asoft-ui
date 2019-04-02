package com.asofttz.ui.react.tools

import com.asofttz.theme.Theme
import kotlinx.css.CSSBuilder
import react.RElementBuilder
import react.RProps

abstract class StyledProps : RProps {
    var css: CSSBuilder.() -> Unit = {}
    var onClick = {}
}

fun <P : StyledProps> RElementBuilder<P>.css(builder: CSSBuilder.() -> Unit) {
    attrs.css = {
        builder()
    }
}

abstract class ThemedProps : StyledProps() {
    var theme = Theme()
}