package com.asofttz.ui.react.layout

import com.asofttz.ui.react.layout.ColumnLayout.Props
import com.asofttz.ui.react.tools.IStyled
import kotlinext.js.jsObject
import kotlinx.css.Display
import kotlinx.css.Gap
import kotlinx.css.GridTemplateColumns
import kotlinx.css.pct
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class ColumnLayout(p: Props) : RComponent<Props, RState>(p) {
    interface Props : IStyled {
        var columns: String
        var gap: String
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
        props.children()
    }
}

fun RBuilder.columnLayout(columns: String = "1fr", gap: String = "1em", handler: RHandler<Props>) = child(ColumnLayout::class.js, jsObject<Props> { }) {
    attrs.columns = columns
    attrs.gap = gap
    attrs.css = {}
    handler()
}