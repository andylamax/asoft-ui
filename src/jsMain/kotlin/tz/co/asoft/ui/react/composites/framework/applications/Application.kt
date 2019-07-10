package tz.co.asoft.ui.react.composites.framework.applications

import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.composites.framework.applications.Application.Props
import tz.co.asoft.ui.react.composites.framework.applications.Application.State
import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLDivElement
import react.*
import react.router.dom.RouteResultHistory
import react.router.dom.RouteResultProps
import react.router.dom.route
import styled.css
import styled.styledDiv
import styled.styledSection
import tz.co.asoft.ui.module.Module
import tz.co.asoft.ui.module.ModuleProps
import tz.co.asoft.ui.module.ScopedRComponent
import tz.co.asoft.ui.react.tools.onDesktop
import tz.co.asoft.ui.react.tools.onMobile
import kotlin.browser.document

class Application : ScopedRComponent<Props, State>() {
    companion object Props : ModuleProps() {
        var onDrawerOpen = {}
        var onLogout = {}
        var title = "Module Name"
        var onRouteResultHistory: (RouteResultHistory) -> Unit = { }
    }

    class State : RState

    private val APP_BAR_ID = "app-bar"

    private fun getPermissions(): Array<String> {
        var perms = arrayOf<String>()
        props.modules.forEach { module ->
            module.permits.forEach { perm ->
                if (!perms.contains(perm))
                    perms += perm
            }
        }
        return perms
    }

    private fun RBuilder.appBar() = styledSection {
        styledDiv {
            css {
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.spaceBetween
                height = 2.5.em
                onMobile {
                    height = 4.em
                }
                width = 100.pct
                backgroundColor = Color(props.theme.primaryColor.main)
                color = Color(props.theme.text.onPrimary.main)
                borderBottom = "solid 2px ${props.theme.tertiaryColor.main}"
                position = Position.sticky
                top = 0.px
                transition(duration = 3.s)
            }

            styledDiv {
                css {
                    display = Display.flex
                    alignItems = Align.center
                    margin(horizontal = 1.em)
                    fontSize = 1.2.em
                }

                styledDiv {
                    css {
                        onDesktop {
                            display = Display.none
                        }
                        height = 1.em
                        width = 1.5.em
                        val c1 = props.theme.text.onPrimary.main
                        val c2 = props.theme.primaryColor.main
                        background = "linear-gradient(0deg,$c1,$c1,transparent,$c2,transparent,$c1,transparent,$c2,transparent,$c1,$c1)"
                    }
                    attrs.onClickFunction = {
                        props.onDrawerOpen()
                    }
                }

                styledDiv {
                    attrs {
                        id = APP_BAR_ID
                    }
                    css {
                        position = Position.relative
                        onDesktop {
                            left = (-1.5).em
                            fontSize = 1.em
                        }
                        margin(horizontal = 1.em)
                        fontSize = 1.2.em
                    }
                    +props.title
                }
            }

            styledDiv {
                css {
                    display = Display.flex
                    flexWrap = FlexWrap.wrap
                    alignItems = Align.center
                    marginRight = 0.5.em
                }

                styledDiv {
                    css {
                        marginRight = 0.5.em
                        fontSize = 1.em
                    }
                    +props.user.name
                }

                styledDiv {
                    css {
                        backgroundColor = props.theme.text.onPrimary.main()
                        borderRadius = 50.pct
                        height = 1.5.em
                        width = 1.5.em
                        cursor = Cursor.pointer
                    }

                    attrs.onClickFunction = {
                        props.onLogout()
                    }
                }
            }
        }
    }

    private fun Module.Section.toComponent(): (RouteResultProps<ModuleProps>) -> ReactElement? = {
        it.match.params.apply {
            theme = props.theme
            themes = props.themes
            user = props.user

            allPerms = getPermissions()

            modules = props.modules

            routeProps = it

            setTitle = {
                (document.getElementById(APP_BAR_ID).unsafeCast<HTMLDivElement?>())?.innerText = it
            }

            setTheme = props.setTheme
        }
        (component.unsafeCast<((RouteResultProps<ModuleProps>) -> ReactElement)>()(it))
    }

    private fun RBuilder.loadModule(module: Module) {
        val mods = module.sections.toMutableList()
        mods.add(0, module.mainSection)
        mods.forEach { section ->
            if (props.user.hasPermits(section.permits) || section.permits.isEmpty()) {
                route("/dashboard${section.route}", true, true) { routeProps: RouteResultProps<ModuleProps> ->
                    props.onRouteResultHistory(routeProps.history)
                    section.toComponent()(routeProps)
                }
            }
        }
    }

    private fun RBuilder.contentSection() = styledSection {
        css {
            height = 100.pct - 2.5.em - 2.px
            width = 100.pct
            overflowX = Overflow.hidden
            overflowY = Overflow.scroll
        }

        props.modules.forEach {
            loadModule(it)
        }
    }

    override fun RBuilder.render(): dynamic = styledSection {
        css {
            position = Position.relative
            backgroundColor = Color(props.theme.backgroundColor.light)
            height = 100.pct
        }
        appBar()
        contentSection()
    }
}