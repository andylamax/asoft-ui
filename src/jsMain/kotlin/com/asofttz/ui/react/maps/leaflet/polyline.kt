//@file:JsModule("react-leaflet-polyline")

package com.asofttz.ui.react.maps.leaflet

import com.asofttz.ui.react.maps.leaflet.LeafletPolyline.Props
import react.Component
import react.RProps
import react.RState

@JsName("default")
external class LeafletPolyline : Component<Props, RState> {
    interface Props : RProps {
        var color: String
        var positions: Array<Array<Double>>
    }

    override fun render(): dynamic
}