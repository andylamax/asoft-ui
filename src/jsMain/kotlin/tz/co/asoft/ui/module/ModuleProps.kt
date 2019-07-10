package tz.co.asoft.ui.module

import tz.co.asoft.ui.theme.Theme
import react.RProps
import react.router.dom.RouteResultProps
import tz.co.asoft.auth.User

abstract class ModuleProps : RProps {
    var theme = Theme()
    var themes = arrayOf<Theme>()
    var user: User = User.fake
    var allPerms = arrayOf<String>()
    var setTitle = { _: String -> }
    var setTheme = { _: Theme -> }
    var modules = arrayOf<Module>()
    var routeProps: RouteResultProps<*>? = null
}