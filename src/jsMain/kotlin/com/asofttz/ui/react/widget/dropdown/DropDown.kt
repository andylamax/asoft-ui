package com.asofttz.ui.react.widget.dropdown

import com.asofttz.theme.main
import com.asofttz.tools.newJsObject
import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.tools.value
import com.asofttz.ui.react.widget.dropdown.DropDownComponent.Props
import com.asofttz.ui.react.widget.dropdown.DropDownComponent.State
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.html.js.onChangeFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv
import styled.styledOption
import styled.styledSelect


class DropDownComponent(p: Props) : RComponent<Props, State>(p) {

    object Props : ThemedProps() {
        var onChange = { _: Int, _: String -> }
        var value = ""
        var options = listOf<String>()
        var isRequired = true
        var name = ""
    }

    class State(p: Props) : RState {
        var value = p.value
    }

    init {
        state = State(p)
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            position = Position.relative
            padding(0.2.em)
            border(2.px, BorderStyle.solid, props.theme.primaryColor.main())
            borderRadius = 0.2.em

            hover {
                boxShadow(props.theme.primaryColor.main(), blurRadius = 10.px, spreadRadius = 2.px)
            }

            active {
                boxShadow(props.theme.primaryColor.main(), blurRadius = 10.px, spreadRadius = 2.px)
            }
            +props.css
            display = Display.flex
        }

        styledSelect {
            attrs {
                required = props.isRequired
                onChangeFunction = {
                    state.value = it.target.value
                    props.onChange(props.options.indexOf(state.value), state.value)
                }
                name = props.name
            }
            css {
                border = "none"
                flexBasis = FlexBasis("100%")
                minWidth = 5.em
                backgroundColor = Color.transparent
                width = 100.pct
                focus {
                    outline = Outline.none
                }
            }
            props.options.forEachIndexed { i, it ->
                styledOption {
                    attrs {
                        value = if (props.isRequired) {
                            if (i == 0) {
                                ""
                            } else {
                                it
                            }
                        } else {
                            it
                        }
                        selected = it == state.value
                    }
                    +it
                }
            }
        }
    }
}

fun RBuilder.dropDown(handler: RHandler<Props>) = child(DropDownComponent::class.js, Props.newJsObject(), handler)