package com.asofttz.ui.react.icons.reacticons

import react.RBuilder
import react.RHandler
import react.RProps

external interface IconProps : RProps {
    var size: Int // in px
    var color: String
    var className: String
}

// from react-icons
//private fun reactIcon(name: String): RClass<IconProps> {
//    var wantedIcon = try {
//        val prov = name.split("/")[0].toLowerCase()
//        val i = name.split("/")[1]
//        require("react-icons/$prov")[i]
//    } catch (c: Throwable) {
//        require("react-icons/fa")["FaSyncAlt"]
//    }
//    if (wantedIcon == undefined) {
//        wantedIcon = require("react-icons/fa")["FaSyncAlt"]
//    }
//    return wantedIcon.unsafeCast<RClass<IconProps>>()
//}

fun RBuilder.icon(name: String, handler: RHandler<IconProps> = {}) = faSyncAlt(handler)

fun RBuilder.loadingIcon() = icon("fa/FaSyncAlt") {
    attrs.className = "fa-spin"
}