package com.asofttz.ui.react.widget.text.textinput

import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.tools.View
import com.asofttz.ui.react.widget.text.textinput.TextInput.Props
import com.asofttz.ui.react.widget.text.textinput.TextInput.State
import kotlinx.css.Color
import kotlinx.css.Outline
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onFocusFunction
import org.w3c.dom.HTMLInputElement
import react.*
import styled.css
import styled.styledDiv
import styled.styledInput
import kotlin.browser.document

class TextInput(p: Props) : RComponent<Props, State>(p) {

    object Props : ThemedProps() {
        var hint = ""
        var type = InputType.text
        var label = ""
        var name = ""
        var onChange = { _: String -> }
        var onBlur = {}
        var isRequired = true
    }

    class State : RState {
        var textValue = ""
        var isFocused = false
    }

    init {
        state = State()
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            +TextInputStyles.root
            +props.css
        }

        styledDiv {
            css {
                if (state.isFocused) {
                    +TextInputStyles.tagNameFocused
                } else {
                    +TextInputStyles.tagNameUnFocused
                }
            }
            +props.label
        }


        styledInput(type = props.type) {
            attrs {
                id = View.getId()
                name = props.name

                if (state.isFocused) {
                    placeholder = props.hint
                }

                required = props.isRequired

                onChangeFunction = {
                    state.textValue = (document.getElementById(id) as HTMLInputElement).value
                    props.onChange(state.textValue)
                }

                onFocusFunction = {
                    setState {
                        isFocused = true
                    }
                }

                onBlurFunction = {
                    setState {
                        isFocused = textValue.isNotEmpty()
                    }
                    props.onBlur()
                }
            }

            css {
                outline = Outline.none
                +TextInputStyles.input
                color = Color(props.theme.text.onBackground.light)
                borderBottomColor = Color(props.theme.primaryColor.main)
            }
        }
    }
}

fun RBuilder.textInput(handler: RHandler<Props> = {}) = child(TextInput::class.js, Props, handler)