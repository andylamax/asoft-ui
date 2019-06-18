package com.asofttz.ui.react.composites.wrapper

import kotlinx.css.pct
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.wrapper(builder: RBuilder.() -> Unit = {}): dynamic = styledDiv {
    css { width = 100.pct }
    builder()
}