package com.asofttz.ui.react.navbar

import com.asofttz.ui.react.tools.onDesktop
import com.asofttz.ui.react.tools.onMobile
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.borderTop
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import styled.StyleSheet

object NavBarStyles : StyleSheet("asoft-view-nav-bar-styles") {
    val mobile_width = 767.px
    val wrapper by css {
        display = Display.flex
        media("only screen and (max-width:$mobile_width)") {
            padding(0.px)
            flexDirection = FlexDirection.column
            maxHeight = 60.vh
        }
        position = Position.fixed
        listStyleType = ListStyleType.none
        justifyContent = JustifyContent.center
        width = 100.pct
        margin(0.px)
        zIndex = 99999
    }

    val ulWrapper by css {
        display = Display.flex
        onMobile {
            flexDirection = FlexDirection.column
            justifyContent = JustifyContent.start
            overflowY = Overflow.auto
        }
        onDesktop {
            justifyContent = JustifyContent.center
            paddingLeft = 25.pct
        }
        padding(0.px)
        position = Position.relative
        listStyleType = ListStyleType.none
        top = 0.px
        width = 100.pct
        margin(0.px)
    }

    val genericMenu by css {
        position = Position.relative
        cursor = Cursor.pointer
        padding(.8.rem, 1.rem)
        transition(duration = 0.3.s)
        whiteSpace = WhiteSpace.nowrap
        zIndex = 9999991
        media("only screen and (min-width:$mobile_width)") {
            children {
                display = Display.none
                opacity = 0
            }

            hover {
                backgroundColor = blackAlpha(0.25)
                children {
                    display = Display.block
                    opacity = 1
                }
            }
        }

        media("only screen and (max-width:$mobile_width)") {
            children {
                display = Display.block
                opacity = 1
            }

            hover {
                borderTop(2.px, BorderStyle.solid, Color.white)
                children {
                    display = Display.block
                    opacity = 1
                }
            }
        }

        a {
            display = Display.block
            color = Color.white
            opacity = 1
            textDecoration = TextDecoration.none
        }
    }


    val topMenu by css {
        +genericMenu
        position = Position.relative
    }

    val subMenuWrapper by css {
        listStyleType = ListStyleType.none
        margin(0.px)
        padding(0.px)
        media("only screen and (min-width:$mobile_width)") {
            position = Position.absolute
            margin(vertical = 0.8.rem, horizontal = 0.rem)
            padding(0.rem)
            marginTop = 0.8.rem
            minWidth = 13.em
            marginLeft = (-1).rem
            borderBottomLeftRadius = 5.px
            borderBottomRightRadius = 5.px
            transition(duration = .2.s)
            hover {
                borderBottomRightRadius = 0.px
            }
        }
    }

    val subMenu by css {
        +genericMenu
        hover {
            paddingLeft = 1.5.em
        }
    }

    val sideMenuWrapper by css {
        listStyleType = ListStyleType.none
        margin(0.px)
        padding(0.px)
        media("only screen and (min-width:$mobile_width)") {
            position = Position.absolute
            left = 100.pct
            top = 0.rem
            margin(vertical = 0.8.rem, horizontal = 0.rem)
            padding(0.rem)
            marginTop = (0).rem
            minWidth = 13.em
            borderBottomRightRadius = 5.px
            transition(duration = .2.s)
            hover {
                borderBottomRightRadius = 0.px
            }
        }
    }

    val sideMenu by css {
        +genericMenu
        hover {
            paddingLeft = 1.5.em
        }
    }

    val mobileToggler by css {
        media("only screen and (min-width:$mobile_width)") {
            display = Display.none
        }
        media("only screen and (max-width:$mobile_width)") {
            display = Display.flex
            alignItems = Align.center
            justifyContent = JustifyContent.spaceBetween
            float = Float.right
            zIndex = 1
        }
    }

    val logoMobile by css {
        marginLeft = 0.5.em
        fontSize = 1.5.em
    }

    val logoDesktop by css {
        alignItems = Align.center
        media("only screen and (max-width:$mobile_width)") {
            display = Display.none
        }

        media("only screen and (min-width:$mobile_width)") {
            display = Display.flex
            marginRight = 1.em
        }
    }
}