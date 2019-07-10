@file:JsModule("react-tabs")

package tz.co.asoft.ui.react.reacttabs

import react.Component
import react.RProps
import react.RState

external interface TabsProps : RProps {
    var selectedIndex: Int
    var onSelect: (Int) -> Unit
}

external class Tabs : Component<TabsProps, RState> {
    override fun render(): dynamic
}

external class Tab : Component<RProps, RState> {
    override fun render(): dynamic
}

external class TabList : Component<RProps, RState> {
    override fun render(): dynamic
}

external class TabPanel : Component<RProps, RState> {
    override fun render(): dynamic
}