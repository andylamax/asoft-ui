package com.asofttz.ui.react.layout

import com.asofttz.ui.react.layout.CenterLayout.Props
import com.asofttz.ui.react.tools.IStyled
import kotlinext.js.jsObject
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class CenterLayout(p: Props) : RComponent<Props, RState>(p) {
    interface Props : IStyled

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            width = 100.pct
            height = 100.pct
            +props.css
        }
        props.children()
    }
}

fun RBuilder.centerLayout(handler: RHandler<Props>) = child(CenterLayout::class.js, jsObject {  }, handler)