package tz.co.asoft.components

import tz.co.asoft.ui.theme.Theme

actual open class CProps actual constructor() {
    @Transient
    var theme = Theme()
}