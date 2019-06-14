@file:JsModule("react-leaflet")
package com.asofttz.ui.react.maps.leaflet

import react.*

external class Map : Component<Map.Props, RState> {
    interface Props : RProps {
        var center: Array<Double>
        var zoom: Number
    }
    override fun render(): dynamic
}

external class TileLayer : Component<TileLayer.Props,RState> {
    interface Props : RProps{
        var url : String
        var attribution : String
    }
    override fun render(): dynamic
}

external class Marker : Component<Marker.Props,RState> {
    interface Props:RProps {
        var position: Array<Double>
    }
    override fun render(): dynamic
}

external class Popup : Component<RProps,RState> {
    override fun render(): dynamic
}