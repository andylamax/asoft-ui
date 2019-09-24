package tz.or.self.ui.components

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.components.CState
import tz.co.asoft.components.Component
import tz.co.asoft.ui.module.ModuleProps
import tz.co.asoft.ui.action.Action
import tz.co.asoft.ui.react.icons.reacticons.mdErrorOutline
import tz.co.asoft.ui.react.widget.button.primaryButton
import tz.co.asoft.ui.theme.main
import tz.or.self.ui.components.Error.Props

class Error(p: Props) : Component<Props, CState>(p) {
    class Props : ModuleProps() {
        var msg = ""
        var actions = listOf<Action>()
        var css: CSSBuilder.() -> Unit = {}
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            height = 100.vh
            width = 100.pct
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            flexDirection = FlexDirection.column
        }

        styledDiv {
            css {
                color = props.theme.error.main()
                fontSize = 3.em
            }
            mdErrorOutline {}
        }

        styledDiv {
            css {
                margin(1.em)
                color = props.theme.error.main()
            }
            +props.msg
        }

        styledDiv {
            css {
                width = 100.pct
                textAlign = TextAlign.center
            }
            props.actions.forEach {
                primaryButton(it.name) {
                    attrs.css = { margin(1.em) }
                    attrs { theme = props.theme }
                    attrs.onClick = { it.handler() }
                }
            }
        }
    }
}