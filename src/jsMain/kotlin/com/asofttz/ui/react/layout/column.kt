package com.asofttz.ui.react.layout

import com.asofttz.ui.react.layout.ColumnLayout.Props
import com.asofttz.ui.react.tools.StyledProps
import kotlinx.css.Display
import kotlinx.css.Gap
import kotlinx.css.GridTemplateColumns
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class ColumnLayout(p: Props) : RComponent<Props, RState>(p) {
    object Props : StyledProps() {
        var columns = "1fr"
        var gap = "1em"
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.grid
            gridTemplateColumns = GridTemplateColumns(props.columns)
            gap = Gap(props.gap)
            width = 100.pct
            height = 100.pct
            +props.css
        }
        attrs.onClickFunction = {props.onClick()}
        props.children()
    }
}

fun RBuilder.columnLayout(columns: String = "1fr", gap: String = "1em", handler: RHandler<Props>) = child(ColumnLayout::class.js, Props) {
    attrs.columns = columns
    attrs.gap = gap
    handler()
}