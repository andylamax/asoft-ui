package com.asofttz.ui.react.layout

import com.asofttz.ui.react.tools.StyledProps
import kotlinx.css.Display
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class HorizontalLayout(p: StyledProps) : RComponent<StyledProps, RState>(p) {
    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            height = 100.pct
            width = 100.pct
            children {
                height = 100.pct
            }
            +props.css
        }
        attrs.onClickFunction = {props.onClick()}
        props.children()
    }
}

fun RBuilder.horizontalLayout(handler: RHandler<StyledProps>) = child(HorizontalLayout::class.js, object : StyledProps() {}, handler)