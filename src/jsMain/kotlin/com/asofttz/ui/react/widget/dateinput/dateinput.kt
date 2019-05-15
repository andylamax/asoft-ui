package com.asofttz.ui.react.widget.dateinput

import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.tools.View
import com.asofttz.ui.react.widget.dateinput.DateInput.Props
import com.asofttz.ui.react.widget.dateinput.DateInput.State
import com.asofttz.ui.react.widget.text.textinput.TextInputStyles
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
import kotlin.js.Date

class DateInput(p: Props) : RComponent<Props, State>(p) {

    object Props : ThemedProps() {
        var hint = ""
        var type = InputType.date
        var label = ""
        var name = ""
            set(value) {
                label = value
                field = value
            }
        var onChange = { _: Date -> }
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


        styledInput {
            attrs {
                id = View.getId()
                if (state.isFocused) {
                    placeholder = props.hint
                }
                name = props.name
                required = props.isRequired

                type = if (state.textValue.isEmpty() && !state.isFocused) InputType.text else props.type

                onChangeFunction = {
                    state.textValue = (document.getElementById(id) as HTMLInputElement).value
                    props.onChange(Date(Date.parse(state.textValue)))
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

fun RBuilder.dateInput(handler: RHandler<Props> = {}) = child(DateInput::class.js, Props, handler)