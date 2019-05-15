package com.asofttz.ui.react.textedit

import com.asofttz.theme.Theme
import kotlinext.js.jsObject
import kotlinext.js.require
import kotlinx.css.*
import react.RBuilder
import react.RHandler
import styled.css
import styled.styledDiv

var isTextEditorCssLoaded = false
fun RBuilder.textEditor(handler: RHandler<EditorProps>) = styledDiv {
    var props = jsObject<EditorProps> {}
    child(Editor::class) {
        if (!isTextEditorCssLoaded) {
            require("react-draft-wysiwyg/dist/react-draft-wysiwyg.css")
            isTextEditorCssLoaded = true
        }
        attrs {
            theme = Theme()
            handler()
            props = this
        }
    }
    css {
        position = Position.relative
        width = 100.pct - 2.em
        margin(horizontal = 1.em)
        border = "solid 2px ${props.theme.primaryColor.main}"

        child("div") {
            padding(1.em)
            minHeight = 50.vh
        }
    }
}