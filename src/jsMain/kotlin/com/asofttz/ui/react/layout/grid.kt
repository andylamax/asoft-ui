package com.asofttz.ui.react.layout

import com.asofttz.ui.react.tools.IStyled
import com.asofttz.ui.react.tools.StyledProps
import kotlinext.js.jsObject
import kotlinx.css.CSSBuilder
import kotlinx.css.Display
import kotlinx.css.GridTemplateAreas
import kotlinx.css.pct
import kotlinx.html.js.onClickFunction
import react.*
import styled.css
import styled.styledDiv
import styled.styledSection

class GridLayout(p: Props) : RComponent<GridLayout.Props, RState>(p) {
    interface Props : IStyled {
        var grids: String
        var gap: String
    }

    override fun RBuilder.render(): dynamic = styledSection {
        css {
            display = Display.grid
            gridTemplateAreas = GridTemplateAreas(props.grids)
            width = 100.pct
            +props.css
        }
        props.children()
    }
}

fun RBuilder.gridLayout(grids: String, gap: String = "1em", handler: RHandler<GridLayout.Props>) = child(GridLayout::class.js, jsObject<GridLayout.Props> {}) {
    attrs.grids = grids
    attrs.gap = gap
    attrs.css = {}
    handler()
}

class GridCell(p: GridCell.Props) : RComponent<GridCell.Props, RState>(p) {
    interface Props : IStyled {
        var area: String
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            put("grid-area", props.area)
            width = 100.pct
            height = 100.pct
            +props.css
        }
        props.children()
    }
}

fun GridBuilder.cell(area: String, handler: RHandler<GridCell.Props>) = child(GridCell::class.js, jsObject<GridCell.Props> {}) {
    attrs.area = area
    handler()
}

typealias GridBuilder = RElementBuilder<GridLayout.Props>