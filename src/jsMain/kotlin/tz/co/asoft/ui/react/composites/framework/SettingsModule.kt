package tz.co.asoft.ui.react.composites.framework

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.html.js.onClickFunction
import react.buildElement
import react.dom.br
import react.router.dom.RouteResultProps
import styled.css
import styled.styledDiv
import tz.co.asoft.ui.module.Module
import tz.co.asoft.ui.module.ModuleProps

object SettingsModule : Module() {
    override val name = "Settings"
    override val author = "andylamax <andylamax@programmer.net>"
    override val version = "0.0.0"
    override var icon = "setting"
    override val mainSection = object : Section() {
        override val name: String = "Settings"
        override val permits: Array<String> = arrayOf(":settings")
        override val route: String = "/settings"
        override val component = Appearance
    }
    override val sections: Array<Section> = arrayOf()
}

private val Appearance = { props: RouteResultProps<ModuleProps> ->
    with(props.match.params) {
        setTitle("Settings")
        buildElement {
            styledDiv {
                css {
                    display = Display.flex
                    width = 100.pct
                    cursor = Cursor.pointer
                    flexDirection = FlexDirection.column
                    padding(1.em)
                }
                +"Appearance Settings | Select Theme"
                br { }
                styledDiv {
                    css {
                        marginTop = 1.em
                        display = Display.flex
                        justifyContent = JustifyContent.stretch
                        width = 100.pct
                    }
                    themes.forEach {
                        styledDiv {
                            attrs.onClickFunction = { _ ->
                                setTheme(it)
                            }
                            css {
                                height = 3.em
                                fontSize = 1.1.em
                                lineHeight = LineHeight("1em")
                                padding(1.em)
                                backgroundColor = Color(it.primaryColor.main)
                                color = Color(it.text.onPrimary.main)
                            }
                            +it.name
                        }
                    }
                }
            }
        }
    }
}