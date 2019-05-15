package com.asofttz.ui.react.widget.text.searchbar

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import styled.StyleSheet

object SearchBarStyles : StyleSheet("asoft-view-search-bar-style") {
    val root by css {
        display = Display.inlineBlock
        position = Position.relative
        backgroundColor = Color.transparent
        border = "none"

        before {
            transition(duration = 1.s)
            position = Position.absolute
            height = 1.px
            left = 5.pct
            bottom = 0.px
            width = 90.pct
            backgroundColor = Color.black
        }

        hover {
            before {
                left = 0.pct
                height = 2.px
                width = 100.pct
            }
        }

        focus {
            left = 0.pct
            height = 2.px
            width = 100.pct
        }
    }
    val input by css {
        transition(duration = 1.s)
        position = Position.relative
        backgroundColor = Color.transparent
        minHeight = 1.5.em
        width = 100.pct
        minWidth = 10.em
        border = "none"
        textAlign = TextAlign.center
        padding(0.5.em, 0.em)
        color = Color.black

        focus {
            transition(duration = 1.s)
            borderBottomStyle = BorderStyle.solid
            borderBottomWidth = 2.px
            borderBottomColor = Color.black
        }
    }
}