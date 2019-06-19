package com.asofttz.ui.react.composites.title

import com.asofttz.ui.react.icons.reacticons.icon
import kotlinx.css.Align
import kotlinx.css.Cursor
import kotlinx.css.Display
import kotlinx.css.em
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledH2

fun RBuilder.titleWithBackArrow(icon: String = "fa/FaArrowLeft", title: String, builder: StyledDOMBuilder<DIV>.() -> Unit = {}) = styledDiv {
    css {
        display = Display.flex
        alignItems = Align.center
        cursor = Cursor.pointer
    }
    icon(icon)
    styledH2 {
        css { marginLeft = 0.5.em }
        +title
    }
    builder()
}