package com.asofttz.ui.react.widget.progress

import com.asofttz.theme.dark
import com.asofttz.theme.light
import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.tools.onDesktop
import com.asofttz.ui.react.tools.onMobile
import com.asofttz.ui.react.widget.progress.ProgressBar.Props
import com.asofttz.ui.react.widget.progress.ProgressBar.State
import kotlinx.css.properties.boxShadowInset
import kotlinx.css.px
import kotlinx.css.rgba
import react.*
import styled.css
import styled.styledProgress
import kotlin.browser.window

class ProgressBar(p: Props) : RComponent<Props, State>(p) {
    object Props : ThemedProps() {
        var value: Number? = null
    }

    class State : RState {
        var dir = 1
        var value = 0.0
    }

    init {
        state = State()
    }

    private var intervalId: Int = -1

    private fun nextIndeterminateValue() {
        intervalId = window.setTimeout({
            setState {
                if (value + dir > 100 || value + dir < 0) {
                    dir *= -1
                }
                value += dir
            }
        }, 10)
    }

    override fun componentWillUnmount() {
        window.clearInterval(intervalId)
    }

    override fun RBuilder.render(): dynamic = styledProgress {
        css {
            put("appearance", "none")
            val decs = arrayOf("-webkit-", "-moz-", "")
            decs.forEach {
                "&::${it}progress-bar" {
                    backgroundColor = props.theme.primaryColor.light()
                    borderRadius = 2.px
                    boxShadowInset(rgba(0, 0, 0, 0.25), blurRadius = 5.px, spreadRadius = 2.px)
                }
            }

            decs.forEach {
                "&::${it}progress-value" {
                    backgroundColor = props.theme.primaryColor.dark()
                    borderRadius = 2.px
                }
            }
            onDesktop {
                height = 5.px
            }

            onMobile {
                height = 10.px
            }
            +props.css
        }
        attrs {
            value = (props.value ?: state.value.apply {
                nextIndeterminateValue()
            }).toString()
            max = "100"
        }
    }
}

fun RBuilder.progressBar(value: Number? = null, handler: RHandler<Props>) = child(ProgressBar::class.js, Props) {
    attrs.value = value
    handler()
}