package com.asofttz.ui.react.maps.leaflet

import kotlinext.js.jsObject
import react.RBuilder
import react.RHandler
import react.RProps

fun RBuilder.map(handler: RHandler<Map.Props> = {}) = child(Map::class.js, jsObject<Map.Props> {
    center = arrayOf(0.0, 0.0)
    zoom = 13
}) {
    tileLayer {
    }
    handler()
}

fun RBuilder.tileLayer(handler: RHandler<TileLayer.Props> = {}) = child(TileLayer::class.js, jsObject {
    url = "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
    attribution = "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
}, handler)

fun RBuilder.marker(position: Array<Double>, handler: RHandler<Marker.Props> = {}) = child(Marker::class.js, jsObject {
    this.position = position
}, handler)

fun RBuilder.popup(handler: RHandler<RProps> = {}) = child(Popup::class.js, jsObject { }, handler)

//fun RBuilder.polyLine(points: Array<Array<Double>>, handler: RHandler<LeafletPolyline.Props> = {}) = child(LeafletPolyline::class.js, jsObject<LeafletPolyline.Props> { }) {
//    attrs.positions = points
//    handler()
//}