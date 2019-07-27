package tz.co.asoft.ui.react.widget.radio

import tz.co.asoft.ui.theme.Theme
import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.widget.radio.RadioButton.Props
import kotlinext.js.js
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.jsStyle
import styled.css
import styled.styledDiv
import styled.styledInput

class RadioButton : RComponent<Props, RState>() {
    object Props : RProps {
        var theme = Theme()
        var label = "Radio Label"
        var name = ""
        var style: dynamic = js { }
        var checked: Boolean? = null
        var onChange = { _: Boolean -> }
        var css: CSSBuilder.() -> Unit = {}
        var value = ""
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        attrs {
            jsStyle = props.style
        }
        css {
            display = Display.inlineFlex
            alignItems = Align.center
            +props.css
        }
        styledInput {
            css {
                position = Position.relative
                width = 1.em
                put("appearance", "none")
                height = 1.em
                borderRadius = 50.pct
                border(2.px, BorderStyle.solid, props.theme.primaryColor.main())
                focus {
                    outline = Outline.none
                }
                checked {
                    after {
                        position = Position.absolute
                        left = 0.1.em
                        top = 0.1.em
                        width = 0.8.em - 4.px
                        height = 0.8.em - 4.px
                        borderRadius = 50.pct
                        backgroundColor = props.theme.primaryColor.main()
                    }
                }
            }
            attrs {
                props.checked?.let {
                    defaultChecked = it
                }
                type = InputType.radio
                name = props.name
                value = props.value
                onChangeFunction = {
                    props.onChange((it.target as HTMLInputElement).checked)
                }
            }
        }

        styledDiv {
            css {
                marginLeft = 0.5.em
            }
            +props.label
        }
    }
}

fun RBuilder.radioButton(label: String = "", handler: RHandler<Props> = {}) = child(RadioButton::class.js, Props) {
    attrs.label = label
    handler()
}