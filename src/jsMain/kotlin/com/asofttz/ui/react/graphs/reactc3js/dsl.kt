package com.asofttz.ui.react.graphs.reactc3js

import kotlinext.js.require
import react.RBuilder
import react.RHandler


var isC33CssLoaded = false

@Deprecated("Use Charts from echarts instead")
fun RBuilder.chart(handler: RHandler<ChartConfiguration>) = child(C3Chart::class) {
    if (!isC33CssLoaded) {
        require("c3/c3.css")
        isC33CssLoaded = true
    }
    handler()
}

typealias XAxisType = String

object XAxisTypeConst {
    val type_category = "category"
    val type_indexed = "indexed"
    val type_timeseries = "timeseries"
}