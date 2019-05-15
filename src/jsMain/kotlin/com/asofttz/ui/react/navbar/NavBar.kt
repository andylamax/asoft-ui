package com.asofttz.ui.react.navbar

import com.asofttz.ui.react.navbar.NavBar.Props
import com.asofttz.ui.react.navbar.NavBar.State
import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.tools.isMobile
import com.asofttz.ui.react.tools.onDesktop
import com.asofttz.ui.react.tools.onMobile
import com.asofttz.ui.react.widget.hamburger.hamburger
import kotlinx.css.*
import kotlinx.css.properties.*
import react.*
import react.router.dom.routeLink
import styled.css
import styled.styledDiv
import styled.styledLi
import styled.styledUl

class NavBar(p: Props) : RComponent<Props, State>(p) {

    object Props : ThemedProps() {
        var menus: List<NavMenu> = mutableListOf()
        var title: String = ""
    }

    class State : RState {
        var showMenu = !isMobile
    }

    init {
        state = State()
    }

    private fun RBuilder.topMenuView(tm: NavMenu) {
        if (state.showMenu)
            styledLi {
                css {
                    +NavBarStyles.topMenu
                }

                routeLink(to = tm.link) {
                    +tm.name
                }
                if (tm.subMenus.isNotEmpty()) {
                    styledUl {
                        css {
                            +NavBarStyles.subMenuWrapper
                            props.theme?.let {
                                backgroundColor = Color(it.primaryColor.dark)
                            }
                        }
                        tm.subMenus.forEach {
                            subMenuView(it)
                        }
                    }
                }
            }
    }

    private fun RBuilder.subMenuView(sm: NavMenu) {
        if (state.showMenu)
            styledLi {
                css {
                    +NavBarStyles.subMenu
                }
                routeLink(to = sm.link) {
                    +sm.name
                }
                if (sm.subMenus.isNotEmpty()) {
                    styledUl {
                        css {
                            +NavBarStyles.sideMenuWrapper
                            props.theme?.let {
                                backgroundColor = Color(it.primaryColor.dark)
                            }
                        }
                        sm.subMenus.forEach {
                            sideMenuView(it)
                        }
                    }
                }
            }
    }

    private fun RBuilder.sideMenuView(sm: NavMenu) {
        if (state.showMenu)
            styledLi {
                css {
                    +NavBarStyles.sideMenu
                }
                routeLink(to = sm.link) {
                    +sm.name
                }
                if (sm.subMenus.isNotEmpty()) {
                    styledUl {
                        css {
                            +NavBarStyles.sideMenuWrapper
                            props.theme?.let {
                                backgroundColor = Color(it.primaryColor.dark)
                            }
                        }
                        sm.subMenus.forEach {
                            sideMenuView(it)
                        }
                    }
                }
            }
    }

    private fun RBuilder.logo() {
        styledDiv {
            css {
                marginLeft = 0.5.em
                fontSize = 1.5.em
                maxHeight = 3.em
            }
            +props.title
        }
    }

    private fun RBuilder.mobileToggler() {
        styledDiv {
            css {
                +NavBarStyles.mobileToggler
                props.theme?.let {
                    backgroundColor = Color(it.primaryColor.dark)
                    color = Color(it.text.onPrimary.dark)
                }
            }
            styledDiv {
                css {
                    +NavBarStyles.logoMobile
                }
                logo()
            }
            hamburger {
                attrs {
                    css = {
                        zIndex = 9999
                        float = Float.right
                    }
                    isOpen = !state.showMenu
                    onToggled = {
                        setState {
                            showMenu = !it
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.handle() {
        val primaryDark = Color(props.theme!!.primaryColor!!.dark)
        styledDiv {
            css {
                position = Position.absolute
                left = 0.px
                top = 0.px
                minHeight = 3.5.em
                maxHeight = 3.5.em
                lineHeight = LineHeight("6em")
                onMobile {
                    display = Display.none
                    top = (-15).px
                }
                fontSize = 1.5.em
                minWidth = 10.pct
                backgroundColor = primaryDark
                zIndex = 999999
            }
            styledDiv {
                css {
                    position = Position.absolute
                    left = 100.pct
                    bottom = 0.px
                    height = 0.em
                    borderTop(3.em, BorderStyle.solid, Color.transparent)
                    borderBottom(3.em, BorderStyle.solid, Color.transparent)
                    borderRight(0.em, BorderStyle.solid, Color.transparent)
                    borderLeft(4.em, BorderStyle.solid, primaryDark)
                    width = 0.pct
                    zIndex = 999999
                }
            }

            styledDiv {
                css {
                    position = Position.relative
                    maxHeight = 3.em
                    bottom = 1.em
                    paddingLeft = 1.em
                    paddingRight = 0.em
                    fontSize = 1.55.em
                    onMobile {
                        bottom = 0.6.em
                    }
                    color = Color.white
                    zIndex = 999999
                }
                +props.title
            }
        }
    }

    override fun RBuilder.render() {
        mobileToggler()
        handle()
        styledDiv {
            css {
                +NavBarStyles.wrapper
                +props.css
            }
            styledUl {
                css {
                    +NavBarStyles.ulWrapper
                    height = if (!isMobile) {
                        LinearDimension.auto
                    } else {
                        if (state.showMenu) {
                            LinearDimension.auto
                        } else {
                            0.em
                        }
                    }
                    props.theme?.let {
                        backgroundColor = Color(it.primaryColor.dark)
                        color = Color(it.text.onPrimary.dark)
                    }
                }

                styledDiv {
                    css {
                        +NavBarStyles.logoDesktop
                        onDesktop {
                            display = Display.none
                        }
                    }
                    logo()
                }

                props.menus.forEach {
                    topMenuView(it)
                }
            }
        }
    }
}

fun RBuilder.navBar(rHandler: RHandler<Props>) = child(NavBar::class.js, Props) {
    attrs {
        title = "Logo"
    }
    rHandler()
}