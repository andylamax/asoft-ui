package com.asofttz.ui.react.composites.modulebar

import com.asofttz.theme.Theme
import com.asofttz.theme.light
import com.asofttz.tools.throttle
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import react.*
import styled.css
import styled.styledDiv
import com.asofttz.ui.react.composites.modulebar.ModuleBar.Props
import com.asofttz.ui.react.widget.text.textinput.textInput

class ModuleBar(p: Props) : RComponent<Props, RState>(p) {
    object Props : RProps {
        var theme = Theme()
        var searchHint = "Type to search"
        var onSearch = { _: String -> }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            position = Position.sticky
            zIndex = 5
            top = 0.em
            padding(0.5.em)
            margin((-0.5).em)
            marginBottom = 1.em
            width = 100.pct + 1.em
            display = Display.flex
            justifyContent = JustifyContent.spaceBetween
            backgroundColor = props.theme.backgroundColor.light()
            color = props.theme.text.onBackground.light()
            boxShadow(Color.gray, offsetY = 2.px, blurRadius = 10.px, spreadRadius = 1.px)
        }

        styledDiv {
            css {
                display = Display.flex
                justifyContent = JustifyContent.flexStart
                children {
                    margin(horizontal = 0.5.em)
                }
                firstChild {
                    marginLeft = 0.em
                }
            }
            props.children()
        }

        styledDiv {
            css {
                display = Display.flex
                justifyContent = JustifyContent.flexEnd
            }
            textInput {
                attrs {
                    theme = props.theme
                    hint = props.searchHint
                    onChange = { key ->
                        throttle(500L) {
                            props.onSearch(key)
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.moduleBar(handler: RHandler<Props>) = child(ModuleBar::class.js, Props, handler)