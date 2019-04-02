@file:JsModule("react-c3js")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE", "unused", "SpellCheckingInspection", "ClassName", "DEPRECATION")

package com.asofttz.ui.react.graphs.reactc3js

import org.w3c.dom.events.Event
import react.Component
import react.RProps
import react.RState
import kotlin.js.Json

@JsName("default")
external class C3Chart : Component<ChartConfiguration, RState> {
    override fun render(): dynamic
}

//=============================
external interface TargetIds {
    var ids: dynamic /* String | Array<String> */
}

external interface Size {
    var width: Number? get() = definedExternally; set(value) = definedExternally
    var height: Number? get() = definedExternally; set(value) = definedExternally
}

external interface Position {
    var top: Number? get() = definedExternally; set(value) = definedExternally
    var right: Number? get() = definedExternally; set(value) = definedExternally
    var bottom: Number? get() = definedExternally; set(value) = definedExternally
    var left: Number? get() = definedExternally; set(value) = definedExternally
}

external interface Resizable {
    var auto: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface Color {
    var pattern: Array<String>? get() = definedExternally; set(value) = definedExternally
    var threshold: Any? get() = definedExternally; set(value) = definedExternally
}

external interface Interaction {
    var enabled: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface Transition {
    var duration: Number? get() = definedExternally; set(value) = definedExternally
}

external interface Step {
    var type: String
}

external interface Line {
    var connectNull: Boolean? get() = definedExternally; set(value) = definedExternally
    var step: Step? get() = definedExternally; set(value) = definedExternally
}

external interface Area {
    var zerobased: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface Dimension {
    var ratio: Number
    var max: Number? get() = definedExternally; set(value) = definedExternally
}

external interface Bar {
    var width: dynamic /* Number | Dimension */ get() = definedExternally; set(value) = definedExternally
    var zerobased: Boolean? get() = definedExternally; set(value) = definedExternally
    var space: Number? get() = definedExternally; set(value) = definedExternally
}

external interface Label {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    val format: ((value: Number, ratio: Number, id: String) -> String)? get() = definedExternally
    var threshold: Number? get() = definedExternally; set(value) = definedExternally
}

external interface Pie {
    var label: Label? get() = definedExternally; set(value) = definedExternally
    var expand: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface Donut {
    var label: Label? get() = definedExternally; set(value) = definedExternally
    var expand: Boolean? get() = definedExternally; set(value) = definedExternally
    var width: Number? get() = definedExternally; set(value) = definedExternally
    var title: String? get() = definedExternally; set(value) = definedExternally
}

external interface `T$14` {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    val format: ((value: Any, ratio: Number) -> String)? get() = definedExternally
}

external interface `T$15` {
    var label: `T$14`? get() = definedExternally; set(value) = definedExternally
    var expand: Boolean? get() = definedExternally; set(value) = definedExternally
    var min: Number? get() = definedExternally; set(value) = definedExternally
    var max: Number? get() = definedExternally; set(value) = definedExternally
    var units: String? get() = definedExternally; set(value) = definedExternally
    var width: Number? get() = definedExternally; set(value) = definedExternally
    var fullCircle: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface `T$16` {
    var type: dynamic /* String /* "linear" */ | String /* "linear-closed" */ | String /* "basis" */ | String /* "basis-open" */ | String /* "basis-closed" */ | String /* "bundle" */ | String /* "cardinal" */ | String /* "cardinal-open" */ | String /* "cardinal-closed" */ | String /* "monotone" */ */ get() = definedExternally; set(value) = definedExternally
}

external interface `T$17` {
    var interpolation: `T$16`? get() = definedExternally; set(value) = definedExternally
}

external interface ChartConfiguration : RProps {
    var bindto: dynamic /* String | HTMLElement | d3.Selection<Any, Any, Any, Any> | Nothing? */ get() = definedExternally; set(value) = definedExternally
    var size: Size? get() = definedExternally; set(value) = definedExternally
    var padding: Position? get() = definedExternally; set(value) = definedExternally
    var resize: Resizable? get() = definedExternally; set(value) = definedExternally
    var color: Color? get() = definedExternally; set(value) = definedExternally
    var interaction: Interaction? get() = definedExternally; set(value) = definedExternally
    var transition: Transition? get() = definedExternally; set(value) = definedExternally
    var oninit: (() -> Unit)? 
    var onrendered: (() -> Unit)? 
    var onmouseover: (() -> Unit)? 
    var onmouseout: (() -> Unit)? 
    var onresize: (() -> Unit)? 
    var onresized: (() -> Unit)? 
    var data: Data
    var unloadBeforeLoad: Boolean
    var onPropsChaged: (oldProps: ChartConfiguration, newProps: ChartConfiguration, chart: ChartAPI) -> Unit
    var axis: Axis? get() = definedExternally; set(value) = definedExternally
    var grid: Grid? get() = definedExternally; set(value) = definedExternally
    var regions: Array<RegionOptions>? get() = definedExternally; set(value) = definedExternally
    var legend: LegendOptions? get() = definedExternally; set(value) = definedExternally
    var tooltip: TooltipOptions? get() = definedExternally; set(value) = definedExternally
    var subchart: SubchartOptions? get() = definedExternally; set(value) = definedExternally
    var zoom: ZoomOptions? get() = definedExternally; set(value) = definedExternally
    var point: PointOptions? get() = definedExternally; set(value) = definedExternally
    var line: Line? get() = definedExternally; set(value) = definedExternally
    var area: Area? get() = definedExternally; set(value) = definedExternally
    var bar: Bar? get() = definedExternally; set(value) = definedExternally
    var pie: Pie? get() = definedExternally; set(value) = definedExternally
    var donut: Donut? get() = definedExternally; set(value) = definedExternally
    var gauge: `T$15`? get() = definedExternally; set(value) = definedExternally
    var spline: `T$17`? get() = definedExternally; set(value) = definedExternally
}

external interface `T$18` {
    var x: String? get() = definedExternally; set(value) = definedExternally
    var value: Array<String>
}

external interface `T$19` {
    @nativeGetter
    operator fun get(key: String): String?

    @nativeSetter
    operator fun set(key: String, value: String)
}

external interface `T$20` {
    var format: (v: Any, id: String, i: Number, j: Number) -> Unit
}

external interface `T$21` {
    @nativeGetter
    operator fun get(key: String): ((v: Any, id: String, i: Number, j: Number) -> Unit)?

    @nativeSetter
    operator fun set(key: String, value: (v: Any, id: String, i: Number, j: Number) -> Unit)
}

external interface `T$22` {
    var format: `T$21`
}

external interface `T$23` {

}

external interface `T$24` {
    var text: String
}

external interface `T$25` {
    var label: `T$24`
}

external interface `T$26` {
    var enabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var grouped: Boolean? get() = definedExternally; set(value) = definedExternally
    var multiple: Boolean? get() = definedExternally; set(value) = definedExternally
    var draggable: Boolean? get() = definedExternally; set(value) = definedExternally
    val isselectable: ((d: Any? /*= null*/) -> Boolean)? get() = definedExternally
}

external interface Data {
    var url: String? get() = definedExternally; set(value) = definedExternally
    var json: dynamic get() = definedExternally; set(value) = definedExternally
    var rows: Array<Array<dynamic /* String | Number | Boolean | Nothing? */>>? get() = definedExternally; set(value) = definedExternally
    var columns: Array<Array<* /* String | Number | Boolean | Nothing? */>>? get() = definedExternally; set(value) = definedExternally
    var mimeType: String? get() = definedExternally; set(value) = definedExternally
    var keys: `T$18`? get() = definedExternally; set(value) = definedExternally
    var x: String? get() = definedExternally; set(value) = definedExternally
    var xs: `T$19`? get() = definedExternally; set(value) = definedExternally
    var xFormat: String? get() = definedExternally; set(value) = definedExternally
    var names: `T$19`? get() = definedExternally; set(value) = definedExternally
    var classes: `T$19`? get() = definedExternally; set(value) = definedExternally
    var groups: Array<Array<String>>? get() = definedExternally; set(value) = definedExternally
    var axes: `T$19`? get() = definedExternally; set(value) = definedExternally
    var type: String? get() = definedExternally; set(value) = definedExternally
    var types: `T$19`? get() = definedExternally; set(value) = definedExternally
    var labels: dynamic /* Boolean | `T$20` | `T$22` */ get() = definedExternally; set(value) = definedExternally
    var order: dynamic /* String | (data: String) -> Unit | Nothing? */ get() = definedExternally; set(value) = definedExternally
    var regions: Json? get() = definedExternally; set(value) = definedExternally
    val color: ((color: String, d: Any) -> dynamic /* String | d3.RGBColor */)? get() = definedExternally
    var colors: `T$23`? get() = definedExternally; set(value) = definedExternally
    var hide: dynamic /* Boolean | Array<String> */ get() = definedExternally; set(value) = definedExternally
    var empty: `T$25`? get() = definedExternally; set(value) = definedExternally
    var selection: `T$26`? get() = definedExternally; set(value) = definedExternally
    val onclick: ((d: Any, element: Any) -> Unit)? get() = definedExternally
    val onmouseover: ((d: Any, element: Any? /*= null*/) -> Unit)? get() = definedExternally
    val onmouseout: ((d: Any, element: Any? /*= null*/) -> Unit)? get() = definedExternally
    val onselected: ((d: Any, element: Any? /*= null*/) -> Unit)? get() = definedExternally
    val onunselected: ((d: Any, element: Any? /*= null*/) -> Unit)? get() = definedExternally
}

external interface Axis {
    var rotated: Boolean? get() = definedExternally; set(value) = definedExternally
    var x: XAxisConfiguration? get() = definedExternally; set(value) = definedExternally
    var y: YAxisConfiguration? get() = definedExternally; set(value) = definedExternally
    var y2: YAxisConfiguration? get() = definedExternally; set(value) = definedExternally
}

external interface `T$27` {
    var left: Number? get() = definedExternally; set(value) = definedExternally
    var right: Number? get() = definedExternally; set(value) = definedExternally
}

external interface `T$28` {
    var text: String
    var position: String
}

external interface XAxisConfiguration {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var type: XAxisType? get() = definedExternally; set(value) = definedExternally
    var localtime: Boolean? get() = definedExternally; set(value) = definedExternally
    var categories: Array<String>? get() = definedExternally; set(value) = definedExternally
    var tick: XTickConfiguration? get() = definedExternally; set(value) = definedExternally
    var max: dynamic /* String | Number | Date */ get() = definedExternally; set(value) = definedExternally
    var min: dynamic /* String | Number | Date */ get() = definedExternally; set(value) = definedExternally
    var padding: `T$27`? get() = definedExternally; set(value) = definedExternally
    var height: Number? get() = definedExternally; set(value) = definedExternally
    var extent: dynamic /* Array<Number> | () -> Array<Number> */ get() = definedExternally; set(value) = definedExternally
    var label: dynamic /* String | `T$28` */ get() = definedExternally; set(value) = definedExternally
}

external interface `T$29` {
    var top: Number? get() = definedExternally; set(value) = definedExternally
    var bottom: Number? get() = definedExternally; set(value) = definedExternally
}

external interface YAxisConfiguration {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var inner: Boolean? get() = definedExternally; set(value) = definedExternally
    var max: Number? get() = definedExternally; set(value) = definedExternally
    var min: Number? get() = definedExternally; set(value) = definedExternally
    var inverted: Boolean? get() = definedExternally; set(value) = definedExternally
    var center: Number? get() = definedExternally; set(value) = definedExternally
    var label: dynamic /* String | `T$28` */ get() = definedExternally; set(value) = definedExternally
    var tick: YTickConfiguration? get() = definedExternally; set(value) = definedExternally
    var padding: `T$29`? get() = definedExternally; set(value) = definedExternally
    var default: Array<Number>? get() = definedExternally; set(value) = definedExternally
}

external interface XTickConfiguration {
    var centered: Boolean? get() = definedExternally; set(value) = definedExternally
    var format: dynamic /* String | (x: dynamic /* Number | Date */) -> dynamic /* String | Number */ */ get() = definedExternally; set(value) = definedExternally
    var culling: dynamic /* Boolean | CullingConfiguration */ get() = definedExternally; set(value) = definedExternally
    var count: Number? get() = definedExternally; set(value) = definedExternally
    var fit: Boolean? get() = definedExternally; set(value) = definedExternally
    var values: Array<dynamic>?  /* Array<String> | Array<Number> */ get() = definedExternally; set(value) = definedExternally
    var rotate: Number? get() = definedExternally; set(value) = definedExternally
    var outer: Boolean? get() = definedExternally; set(value) = definedExternally
    var width: Number? get() = definedExternally; set(value) = definedExternally
    var multiline: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface YTickConfiguration {
    val format: ((x: Number) -> String)? get() = definedExternally
    var outer: Boolean? get() = definedExternally; set(value) = definedExternally
    var values: Array<Number>? get() = definedExternally; set(value) = definedExternally
    var count: Number? get() = definedExternally; set(value) = definedExternally
}

external interface CullingConfiguration {
    var max: Number
}

external interface `T$30` {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var lines: Array<LineOptions>? get() = definedExternally; set(value) = definedExternally
}

external interface Grid {
    var x: `T$30`? get() = definedExternally; set(value) = definedExternally
    var y: `T$30`? get() = definedExternally; set(value) = definedExternally
}

external interface LineOptions {
    var value: dynamic /* String | Number | Date */
    var text: String? get() = definedExternally; set(value) = definedExternally
    var axis: String? get() = definedExternally; set(value) = definedExternally
    var position: String? get() = definedExternally; set(value) = definedExternally
    var `class`: String? get() = definedExternally; set(value) = definedExternally
}

external interface RegionOptions {
    var axis: String? get() = definedExternally; set(value) = definedExternally
    var start: dynamic /* String | Number | Date */ get() = definedExternally; set(value) = definedExternally
    var end: dynamic /* String | Number | Date */ get() = definedExternally; set(value) = definedExternally
    var `class`: String? get() = definedExternally; set(value) = definedExternally
}

external interface `T$31` {
    var anchor: String? get() = definedExternally; set(value) = definedExternally
    var x: Number? get() = definedExternally; set(value) = definedExternally
    var y: Number? get() = definedExternally; set(value) = definedExternally
    var step: Number? get() = definedExternally; set(value) = definedExternally
}

external interface `T$32` {
    val onclick: ((id: Any) -> Unit)? get() = definedExternally
    val onmouseover: ((id: Any) -> Unit)? get() = definedExternally
    val onmouseout: ((id: Any) -> Unit)? get() = definedExternally
    var tile: Size? get() = definedExternally; set(value) = definedExternally
}

external interface LegendOptions {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var hide: dynamic /* String | Boolean | Array<String> */ get() = definedExternally; set(value) = definedExternally
    var position: String? get() = definedExternally; set(value) = definedExternally
    var inset: `T$31`? get() = definedExternally; set(value) = definedExternally
    var padding: Number? get() = definedExternally; set(value) = definedExternally
    var item: `T$32`? get() = definedExternally; set(value) = definedExternally
}

external interface `T$33` {
    val title: ((x: Any) -> String)? get() = definedExternally
    val name: ((name: String, ratio: Number, id: String, index: Number) -> String)? get() = definedExternally
    val value: ((value: Any, ratio: Number, id: String, index: Number) -> String)? get() = definedExternally
}

external interface `T$34` {
    var top: Number
    var left: Number
}

external interface TooltipOptions {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var grouped: Boolean? get() = definedExternally; set(value) = definedExternally
    var format: `T$33`? get() = definedExternally; set(value) = definedExternally
    val position: ((data: Any, width: Number, height: Number, element: Any) -> `T$34`)? get() = definedExternally
    val contents: ((data: Any, defaultTitleFormat: String, defaultValueFormat: String, color: Any) -> String)? get() = definedExternally
    var order: dynamic /* String | Array<Any> | (data1: Any, data2: Any) -> Number | Nothing? */ get() = definedExternally; set(value) = definedExternally
}

external interface `T$35` {
    var height: Number
}

external interface SubchartOptions {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var size: `T$35`? get() = definedExternally; set(value) = definedExternally
    val onbrush: ((domain: Any) -> Unit)? get() = definedExternally
}

external interface ZoomOptions {
    var enabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var type: dynamic /* String /* "scroll" */ | String /* "drag" */ */ get() = definedExternally; set(value) = definedExternally
    var rescale: Boolean? get() = definedExternally; set(value) = definedExternally
    var extent: dynamic /* JsTuple<Number, Number> */ get() = definedExternally; set(value) = definedExternally
    val onzoom: ((domain: Any) -> Unit)? get() = definedExternally
    val onzoomstart: ((event: Event) -> Unit)? get() = definedExternally
    val onzoomend: ((domain: Any) -> Unit)? get() = definedExternally
}

external interface `T$36` {
    var enabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var r: Number? get() = definedExternally; set(value) = definedExternally
}

external interface `T$37` {
    var expand: `T$36`
}

external interface `T$38` {
    var r: Number? get() = definedExternally; set(value) = definedExternally
}

external interface PointOptions {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var r: dynamic /* Number | (d: Any) -> Number */ get() = definedExternally; set(value) = definedExternally
    var focus: `T$37`? get() = definedExternally; set(value) = definedExternally
    var select: `T$38`? get() = definedExternally; set(value) = definedExternally
}

external interface `T$39` {
    var withLegend: Boolean
}

external interface `T$40` {

}

external interface `T$41` {
    var url: String? get() = definedExternally; set(value) = definedExternally
    var json: Any? get() = definedExternally; set(value) = definedExternally
    var keys: `T$18`? get() = definedExternally; set(value) = definedExternally
    var rows: Array<Array<dynamic /* String | Number | Boolean | Nothing? */>>? get() = definedExternally; set(value) = definedExternally
    var columns: Array<Array<dynamic /* String | Number | Boolean | Nothing? */>>? get() = definedExternally; set(value) = definedExternally
    var xs: `T$19`? get() = definedExternally; set(value) = definedExternally
    var names: `T$19`? get() = definedExternally; set(value) = definedExternally
    var classes: `T$19`? get() = definedExternally; set(value) = definedExternally
    var categories: Array<String>? get() = definedExternally; set(value) = definedExternally
    var axes: `T$19`? get() = definedExternally; set(value) = definedExternally
    var colors: `T$40`? get() = definedExternally; set(value) = definedExternally
    var type: String? get() = definedExternally; set(value) = definedExternally
    var types: `T$19`? get() = definedExternally; set(value) = definedExternally
    var unload: dynamic /* String | Boolean | Array<String> */ get() = definedExternally; set(value) = definedExternally
    val done: (() -> Any)? get() = definedExternally
}

external interface `T$42` {
    var json: Any? get() = definedExternally; set(value) = definedExternally
    var keys: `T$18`? get() = definedExternally; set(value) = definedExternally
    var rows: Array<Array<dynamic /* String | Number | Boolean | Nothing? */>>? get() = definedExternally; set(value) = definedExternally
    var columns: Array<Array<dynamic /* String | Number | Boolean | Nothing? */>>? get() = definedExternally; set(value) = definedExternally
    var to: Any? get() = definedExternally; set(value) = definedExternally
    var length: Number? get() = definedExternally; set(value) = definedExternally
    var duration: Number? get() = definedExternally; set(value) = definedExternally
    val done: (() -> Any)? get() = definedExternally
}

external interface `T$43` {
    var value: dynamic /* String | Number */ get() = definedExternally; set(value) = definedExternally
    var `class`: String? get() = definedExternally; set(value) = definedExternally
}

external interface `T$44` {
    @nativeInvoke
    operator fun invoke(regions: Array<Any>)

    fun add(regions: Any?)
    fun add(regions: Array<Any>)
    fun remove(args: `T$43`? = definedExternally /* null */)
}

external interface `T$45` {
    @nativeInvoke
    operator fun invoke(targetIds: String? = definedExternally /* null */): Data

    @nativeInvoke
    operator fun invoke(targetIds: Array<String>? = definedExternally /* null */): Data

    fun shown(targetIds: String? = definedExternally /* null */): Data
    fun shown(targetIds: Array<String>? = definedExternally /* null */): Data
    fun values(targetIds: String? = definedExternally /* null */): Array<Any>
    fun values(targetIds: Array<String>? = definedExternally /* null */): Array<Any>
    fun names(names: `T$19`? = definedExternally /* null */): `T$19`
    fun colors(colors: `T$40`? = definedExternally /* null */): `T$19`
    fun axes(axes: `T$19`? = definedExternally /* null */): `T$19`
    @nativeInvoke
    operator fun invoke(): Data

    fun shown(): Data
    fun values(): Array<Any>
}

external interface `T$46` {
    @nativeGetter
    operator fun get(key: String): Array<dynamic /* String | Number | Boolean | Nothing? */>?

    @nativeSetter
    operator fun set(key: String, value: Array<dynamic /* String | Number | Boolean | Nothing? */>)
}

external interface `T$47` {
    @nativeGetter
    operator fun get(key: String): Number?

    @nativeSetter
    operator fun set(key: String, value: Number)
}

external interface `T$48` {
    var min: dynamic /* Number | `T$47` */ get() = definedExternally; set(value) = definedExternally
    var max: dynamic /* Number | `T$47` */ get() = definedExternally; set(value) = definedExternally
}

external interface `T$49` {
    var min: dynamic /* Number | `T$47` */
    var max: dynamic /* Number | `T$47` */
}

external interface `T$50` {
    fun labels(labels: `T$19`? = definedExternally /* null */): `T$19`
    fun min(min: Number? = definedExternally /* null */): dynamic /* Number | `T$47` */
    fun min(min: `T$47`? = definedExternally /* null */): dynamic /* Number | `T$47` */
    fun max(max: Number? = definedExternally /* null */): dynamic /* Number | `T$47` */
    fun max(max: `T$47`? = definedExternally /* null */): dynamic /* Number | `T$47` */
    fun range(range: `T$48`? = definedExternally /* null */): `T$49`
    fun min(): dynamic /* Number | `T$47` */
    fun max(): dynamic /* Number | `T$47` */
}

external interface `T$51` {
    fun show(targetIds: String? = definedExternally /* null */)
    fun show(targetIds: Array<String>? = definedExternally /* null */)
    fun hide(targetIds: String? = definedExternally /* null */)
    fun hide(targetIds: Array<String>? = definedExternally /* null */)
    fun show()
    fun hide()
}

external interface `T$52` {
    @nativeInvoke
    operator fun invoke(domain: Array<Number>? = definedExternally /* null */): Array<Number>

    fun enable(enabled: Boolean)
}

external interface ChartAPI {
    fun focus(targetIds: String? = definedExternally /* null */)
    fun focus(targetIds: Array<String>? = definedExternally /* null */)
    fun defocus(targetIds: String? = definedExternally /* null */)
    fun defocus(targetIds: Array<String>? = definedExternally /* null */)
    fun revert(targetIds: String? = definedExternally /* null */)
    fun revert(targetIds: Array<String>? = definedExternally /* null */)
    fun show(targetIds: String? = definedExternally /* null */, options: `T$39`? = definedExternally /* null */)
    fun show(targetIds: Array<String>? = definedExternally /* null */, options: `T$39`? = definedExternally /* null */)
    fun hide(targetIds: String? = definedExternally /* null */, options: `T$39`? = definedExternally /* null */)
    fun hide(targetIds: Array<String>? = definedExternally /* null */, options: `T$39`? = definedExternally /* null */)
    fun toggle(targetIds: String? = definedExternally /* null */, options: `T$39`? = definedExternally /* null */)
    fun toggle(targetIds: Array<String>? = definedExternally /* null */, options: `T$39`? = definedExternally /* null */)
    fun load(args: `T$41`)
    fun unload(targetIds: TargetIds? = definedExternally /* null */, done: (() -> Any)? = definedExternally /* null */): Any
    fun flow(args: `T$42`)
    fun select(ids: Array<String>? = definedExternally /* null */, indices: Array<Number>? = definedExternally /* null */, resetOthers: Boolean? = definedExternally /* null */)
    fun unselect(ids: Array<String>? = definedExternally /* null */, indices: Array<Number>? = definedExternally /* null */)
    fun selected(targetId: String? = definedExternally /* null */): Data
    fun transform(type: String, targetIds: String? = definedExternally /* null */)
    fun transform(type: String, targetIds: Array<String>? = definedExternally /* null */)
    fun groups(groups: Array<Array<String>>)
    var xgrids: GridOperations
    var ygrids: GridOperations
    var regions: `T$44`
    var data: `T$45`
    fun category(index: Number, category: String? = definedExternally /* null */): String
    fun categories(categories: Array<String>? = definedExternally /* null */): Array<String>
    fun color(targetId: String): String
    fun x(x: Array<dynamic /* String | Number | Boolean | Nothing? */>? = definedExternally /* null */): Array<dynamic /* String | Number | Boolean | Nothing? */>
    fun xs(xs: `T$46`? = definedExternally /* null */): `T$46`
    var axis: `T$50`
    var legend: `T$51`
    var zoom: `T$52`
    fun unzoom()
    fun resize(size: Size? = definedExternally /* null */)
    fun flush()
    fun destroy()
    fun focus()
    fun defocus()
    fun revert()
    fun show()
    fun hide()
    fun toggle()
    fun transform(type: String)
}

external interface GridOperations {
    @nativeInvoke
    operator fun invoke(grids: Array<Any>)

    fun add(grids: Any?)
    fun add(grids: Array<Any>)
    fun remove(args: `T$43`? = definedExternally /* null */)
}