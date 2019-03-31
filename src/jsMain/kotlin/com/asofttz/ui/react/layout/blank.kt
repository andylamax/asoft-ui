package com.asofttz.ui.react.layout

import kotlinx.css.LinearDimension
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.blank(w: LinearDimension? = null, h: LinearDimension? = null) = styledDiv {
    css {
        w?.let { width = it }
        h?.let { height = it }
    }
}