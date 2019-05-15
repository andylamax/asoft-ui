package com.asofttz.ui.react.widget.icon

import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.widget.icon.IconComponent.Props
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv
import styled.styledImg

enum class IconType {
    normal, warning, danger, info
}

class Icon  {
    var src = ""

    var size = 1.em

    var text = "Icon"

    var forceAsIs = false

    var type = IconType.normal
}

class IconComponent : RComponent<Props, RState>() {

    object Props : ThemedProps() {
        lateinit var icon: Icon
    }

    override fun RBuilder.render() {
        if (props.icon.src.isEmpty()) {
            styledDiv {
                css {
                    display = Display.inlineBlock
                    minHeight = props.icon.size
                    minWidth = props.icon.size
                    lineHeight = LineHeight(props.icon.size.value)
                    borderRadius = 50.pct
                    textAlign = TextAlign.center
                    border = "solid 1px #fff"
                    borderColor = Color.white
                    color = Color.black
                    margin(0.em, 0.5.em)
                    props.theme?.let {
                        borderColor = Color(it.primaryColor.main)
                        color = Color(it.text.onPrimary.main)
                    }
                    +props.css
                }
                if (props.icon.forceAsIs) {
                    +props.icon.text
                } else {
                    +props.icon.text[0].toString().toUpperCase()
                }
            }
        } else {
            styledImg(src = props.icon.src) {
                css {
                    height = props.icon.size
                    width = props.icon.size
                }
            }
        }
    }
}

fun RBuilder.icon(text: String = "Icon", src: String = "", handler: RHandler<Props> = {}) = child(IconComponent::class) {
    attrs {
        icon = Icon().apply {
            this.text = text
            this.src = src
        }
        handler()
    }
}