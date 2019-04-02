package com.asofttz.ui.react.layout

import com.asofttz.ui.react.tools.StyledProps
import kotlinx.css.Position
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class StackedLayout(p: StyledProps) : RComponent<StyledProps, RState>(p) {
    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            position = Position.relative
            width = 100.pct
            height = 100.pct
            children {
                position = Position.absolute
            }
            +props.css
        }
        attrs.onClickFunction = {props.onClick()}
        props.children()
    }
}

fun RBuilder.stackedLayout(handler: RHandler<StyledProps>) = child(StackedLayout::class.js, object : StyledProps() {}, handler)