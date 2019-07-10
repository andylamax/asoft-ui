package tz.co.asoft.ui.theme

import kotlin.js.JsName

@JsName("Theme")
open class Theme {
    var name = "Blue Theme"
    var primaryColor = ThemeColors("#539cff", "#0066cc", "#003d9a")
    var secondaryColor = ThemeColors("#6ceb60", "#2eb82e", "#088700")
    var tertiaryColor = ThemeColors("#ffff5a", "#ffff00", "#c7cc00")
    var backgroundColor = ThemeColors("#ffffff", "#f5f5f5", "#c2c2c2")
    var error = ThemeColors(main = "#ff0000")
    var alternateColor = arrayOf(ThemeColors(main = "#253646"))

    var text = TypoColors()
}
