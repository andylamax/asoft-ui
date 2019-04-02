package com.asofttz.ui.react.layout

import com.asofttz.ui.react.tools.StyledProps
import kotlinx.css.Align
import kotlinx.css.Display
import kotlinx.css.JustifyContent
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class CenterLayout(p: StyledProps) : RComponent<StyledProps, RState>(p) {
    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            width = 100.pct
            height = 100.pct
            +props.css
        }
        attrs.onClickFunction = { props.onClick() }
        props.children()
    }
}

fun RBuilder.centerLayout(handler: RHandler<StyledProps>) = child(CenterLayout::class.js, object : StyledProps() {}, handler)