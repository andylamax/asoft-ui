package com.asofttz.ui.react.composites.badge

import com.asofttz.ui.badge.Badge
import com.asofttz.ui.react.composites.badge.BadgeUI.Props
import com.asofttz.ui.react.icons.reacticons.icon
import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.tools.onDesktop
import com.asofttz.ui.react.tools.onMobile
import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv

class BadgeUI(p: Props) : RComponent<Props, RState>(p) {
    object Props : ThemedProps() {
        var badge: Badge? = null
    }

    private fun RBuilder.loading() = styledDiv {
        css {
            flexBasis = FlexBasis("100.pct")
            width = 100.pct
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            fontSize = 3.5.em
        }
        styledDiv { icon("fa/FaSyncAlt") }
    }

    private fun RBuilder.content() {
        styledDiv {
            css {
                flexBasis = FlexBasis("70.pct")
                display = Display.flex
                flexDirection = FlexDirection.column
            }
            styledDiv {
                css {
                    fontSize = 2.2.em
                }
                +props.badge!!.value
            }
            styledDiv {
                +props.badge!!.title
            }
        }

        styledDiv {
            css {
                flexBasis = FlexBasis("30.pct")
                fontSize = 3.5.em
            }
            icon(props.badge!!.icon)
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            display = Display.flex
            onDesktop {
                flexBasis = FlexBasis("20%")
            }

            onMobile {
                flexBasis = FlexBasis("48%")
            }

            minHeight = 5.em
            padding(horizontal = 1.em)
            marginBottom = 1.em
            justifyContent = JustifyContent.spaceBetween
            alignItems = Align.center
            backgroundColor = Color("#${(5263440..16777215).random().toString(16)}").withAlpha((2..7).random().toDouble() / 10)
            cursor = Cursor.pointer
            border = "solid 1px transparent"
            transition(duration = 0.5.s)
            hover {
                border = "solid 1px black"
            }
            +props.css
            attrs.onClickFunction = { props.onClick() }
        }

        if (props.badge != null) {
            content()
        } else {
            loading()
        }
    }
}

fun RBuilder.badge(b: Badge? = null, handler: RHandler<Props> = {}) = child(BadgeUI::class.js, Props) {
    attrs.badge = b
    handler()
}