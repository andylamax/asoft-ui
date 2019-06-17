package com.asofttz.ui.react.layout

import com.asofttz.ui.react.layout.HorizontalLayout.Props
import com.asofttz.ui.react.tools.IStyled
import com.asofttz.ui.react.tools.StyledProps
import kotlinext.js.jsObject
import kotlinx.css.CSSBuilder
import kotlinx.css.Display
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class HorizontalLayout(p: Props) : RComponent<Props, RState>(p) {
    interface Props : IStyled

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            width = 100.pct
            +props.css
        }
        props.children()
    }
}

fun RBuilder.horizontalLayout(handler: RHandler<Props>) = child(HorizontalLayout::class.js, jsObject<Props> { }) {
    attrs { css = {} }
    handler()
}