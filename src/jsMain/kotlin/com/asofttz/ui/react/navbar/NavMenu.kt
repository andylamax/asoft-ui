package com.asofttz.ui.react.navbar

import com.asofttz.ui.react.widget.icon.Icon

class NavMenu(val icon: Icon? = null, val name: String = "Menu", val link: String = "#") {
    val subMenus = mutableListOf<NavMenu>()

    fun subMenu(icon: Icon? = null, name: String = "Menu", link: String = "#", builder: NavMenu.() -> Unit = {}) {
        val sm = NavMenu(icon, name, link).apply(builder)
        subMenus.add(sm)
    }

    companion object {
        val TEST_NAV_MENU = navMenuBuilder {
            menu(name = "Menu 1")
            menu(name = "Menu 2") {
                subMenu(name = "Menu 2 - 1")
                subMenu(name = "Menu 2 - 2")
            }
            menu(name = "Menu 3") {
                subMenu(name = "Menu 3 - 1")
                subMenu(name = "Menu 3 - 2") {
                    subMenu(name = "Menu 3 - 2 - 1")
                    subMenu(name = "Menu 3 - 2 - 2")
                }
            }
        }
    }
}

class NavMenuBuilder {
    internal val menus = mutableListOf<NavMenu>()

    fun menu(icon: Icon? = null, name: String = "Menu", link: String = "#", builder: NavMenu.() -> Unit = {}) {
        val menu = NavMenu(icon, name, link).apply(builder)
        menus.add(menu)
    }
}

fun navMenuBuilder(builer: NavMenuBuilder.() -> Unit) = NavMenuBuilder().apply(builer).menus

