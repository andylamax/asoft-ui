package tz.co.asoft.ui.react.widget.checkbox

import kotlinx.css.*
import tz.co.asoft.ui.tools.newJsObject
import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.widget.checkbox.CheckBox.Props
import tz.co.asoft.ui.react.widget.checkbox.CheckBox.State
import kotlinx.html.js.onClickFunction
import react.*
import styled.css
import styled.styledDiv

class CheckBox(p: Props) : RComponent<Props, State>(p) {
    object Props : ThemedProps() {
        var size = 1.0   // in em
        var borderSize = 2 // in px
        var lineSize = 1 // in px
        var onChanged = { _: Boolean -> }
        var checked: Boolean? = null
        var label = ""
        var name = ""
            set(value) {
                label = value
                field = value
            }
        var isRequired = true
    }

    class State(p: Props = Props) : RState {
        var checked = p.checked ?: false
    }

    init {
        state = State(p)
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        val borderSize = props.borderSize //in px
        val size = props.size // in em
        val lineSize = props.lineSize // in px

        css {
            +CheckBoxStyles.root
        }

        styledDiv {
            attrs.onClickFunction = {
                setState {
                    checked = checked.not()
                    props.onChanged(checked)
                }
            }
            css {
                +CheckBoxStyles.root
                width = size.em
                height = size.em
                border = "solid ${borderSize}px ${props.theme.primaryColor.main}"
            }

            styledDiv {
                css {
                    props.theme.let {
                        borderRight = "solid ${lineSize}px ${it.primaryColor.main}"
                    }
                    if (state.checked) {
                        +CheckBoxStyles.tailLeftChecked
                    } else {
                        left = (-lineSize / 2).px
                        +CheckBoxStyles.tailLeftUnChecked
                    }
                }
            }

            styledDiv {
                css {
                    borderLeft = "solid ${lineSize}px ${props.theme.primaryColor.main}"
                    if (state.checked) {
                        +CheckBoxStyles.tailRightChecked
                    } else {
                        right = (-lineSize / 2).px
                        +CheckBoxStyles.tailRightUnChecked
                    }
                }
            }
        }

        styledDiv {
            css {
                +CheckBoxStyles.root
                paddingLeft = 0.3.em
            }
            +props.label
        }
    }
}

fun RBuilder.checkbox(label: String = "", handler: RHandler<Props> = {}) = child(CheckBox::class.js, Props.newJsObject()) {
    attrs.label = label
    handler()
}