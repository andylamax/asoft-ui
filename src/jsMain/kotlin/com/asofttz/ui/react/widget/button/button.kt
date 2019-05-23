package com.asofttz.ui.react.widget.button

import com.asofttz.theme.dark
import com.asofttz.theme.main
import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.widget.button.Button.Props
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledButton

class Button(p: Props) : RComponent<Props, RState>(p) {

    object Props : ThemedProps() {
        var type = Type.Primary
        var text = ""
        var isSubmit = false
    }

    enum class Type {
        Primary, Normal, Raised, Secondary
    }

    private val bgCol
        get() = when (props.type) {
            Type.Normal -> props.theme.backgroundColor
            Type.Secondary -> props.theme.secondaryColor
            else -> props.theme.primaryColor
        }

    private val borderCol
        get() = if (props.type == Type.Normal) {
            props.theme.primaryColor
        } else bgCol

    private val textCol
        get() = when (props.type) {
            Type.Normal -> props.theme.text.onBackground.main()
            Type.Secondary -> props.theme.text.onSecondary.main()
            else -> props.theme.text.onPrimary.main()
        }

    override fun RBuilder.render(): dynamic = styledButton {
        css {
            backgroundColor = bgCol.main()
            border(2.px, BorderStyle.solid, borderCol.main())
            borderRadius = 4.px
            color = textCol
            padding(0.4.em)
            cursor = Cursor.pointer
            put("font-size", "inherit")
            +props.css

            hover {
                backgroundColor = bgCol.dark()
                border(2.px, BorderStyle.solid, borderCol.dark())
            }

            focus { outline = Outline.none }
        }

        if (props.isSubmit) {
            attrs.type = ButtonType.submit
        }

        attrs.onClickFunction = {
            props.onClick()
        }

        +props.text
    }
}

fun RBuilder.button(
        text: String = "P Button",
        type: Button.Type = Button.Type.Primary,
        handler: RHandler<Props> = {}
) = child(Button::class.js, Props) {
    attrs.text = text
    attrs.type = type
    handler()
}

fun RBuilder.primaryButton(text: String = "P Button", handler: RHandler<Props> = {}) =
        button(text, Button.Type.Primary, handler = handler)

fun RBuilder.normalButton(text: String = "N Button", handler: RHandler<Props> = {}) =
        button(text, Button.Type.Normal, handler)

fun RBuilder.secondaryButton(text: String = "S Button", handler: RHandler<Props> = {}) =
        button(text, Button.Type.Secondary, handler)