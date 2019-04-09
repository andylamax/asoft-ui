package com.asofttz.ui.react.layout

import com.asofttz.ui.react.layout.VerticalLayout.Props
import com.asofttz.ui.react.tools.StyledProps
import kotlinx.css.Align
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class VerticalLayout(p: Props) : RComponent<Props, RState>(p) {
    object Props : StyledProps() {
        var isCentered = false
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            width = 100.pct
            height = 100.pct
            if (props.isCentered) {
                alignItems = Align.center
            } else
                children {
                    width = 100.pct
                }
            +props.css
        }
        attrs.onClickFunction = { props.onClick() }
        props.children()
    }
}

fun RBuilder.verticalLayout(handler: RHandler<Props>) = child(VerticalLayout::class.js, Props) {
    attrs.css = {}
    handler()
}

fun RBuilder.verticalCenteredLayout(handler: RHandler<Props>) = child(VerticalLayout::class.js, Props) {
    attrs.isCentered = true
    handler()
}