package tz.co.asoft.ui.react.widget.button

import tz.co.asoft.ui.theme.Theme
import tz.co.asoft.ui.theme.main
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.js.onClickFunction
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.fab(theme: Theme, onClick: () -> Unit = {}) = styledDiv {
    css {
        position = Position.absolute
        bottom = 0.5.em
        right = 0.5.em
        backgroundColor = theme.primaryColor.main()
        fontSize = 2.em
        width = 1.em
        height = 1.em
        display = Display.flex
        alignItems = Align.center
        justifyContent = JustifyContent.center
        borderRadius = 50.pct
        color = theme.text.onPrimary.main()
        boxShadow(Color.gray, blurRadius = 4.px, spreadRadius = 2.px)
        transition(duration = 0.2.s)
        hover {
            boxShadow(Color.gray, blurRadius = 6.px, spreadRadius = 3.px)
        }

        active {
            boxShadow(Color.gray, blurRadius = 2.px, spreadRadius = 1.px)
        }
    }

    attrs.onClickFunction = {
        onClick()
    }
    attrs["data-value"] = "+"
    +"+"
}