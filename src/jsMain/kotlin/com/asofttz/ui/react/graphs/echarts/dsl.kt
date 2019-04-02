package com.asofttz.ui.react.graphs.echarts

import react.RBuilder
import react.RHandler

fun RBuilder.chart(handler: RHandler<ReactEcharts.Props>) = child(ReactEcharts::class, handler)