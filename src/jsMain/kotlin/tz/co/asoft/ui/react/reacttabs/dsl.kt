package tz.co.asoft.ui.react.reacttabs

import kotlinext.js.require
import react.RBuilder
import react.RHandler
import react.RProps

fun RBuilder.tabs(handler: RHandler<TabsProps> = {}) = child(Tabs::class) {
    if (!isReactTabCssLoaded) {
        loadReactTabsCss()
    }
    handler()
}

fun RBuilder.tab(handler: RHandler<RProps> = {}) = child(Tab::class, handler)
fun RBuilder.tabList(handler: RHandler<RProps> = {}) = child(TabList::class, handler)
fun RBuilder.tabPanel(handler: RHandler<RProps> = {}) = child(TabPanel::class, handler)


var isReactTabCssLoaded = false

private fun loadReactTabsCss() {
    require("react-tabs/style/react-tabs.css")
    isReactTabCssLoaded = true
}