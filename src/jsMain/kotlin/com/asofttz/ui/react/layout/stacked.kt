package com.asofttz.ui.react.layout

import com.asofttz.ui.react.layout.StackedLayout.Props
import com.asofttz.ui.react.tools.IStyled
import com.asofttz.ui.react.tools.StyledProps
import kotlinx.css.CSSBuilder
import kotlinx.css.Position
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class StackedLayout(p: Props) : RComponent<Props, RState>(p) {
    object Props : IStyled {
        override var css: CSSBuilder.() -> Unit = {}
    }

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
        props.children()
    }
}

fun RBuilder.stackedLayout(handler: RHandler<Props>) = child(StackedLayout::class.js, Props, handler)