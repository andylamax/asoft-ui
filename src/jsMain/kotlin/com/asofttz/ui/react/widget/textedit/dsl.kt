package com.asofttz.ui.react.widget.textedit

import com.asofttz.theme.Theme
import kotlinext.js.jsObject
import kotlinext.js.require
import kotlinx.css.GridColumn
import kotlinx.css.em
import kotlinx.css.padding
import kotlinx.css.vh
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
        gridColumn = GridColumn("1/4")
        border = "solid 2px ${props.theme.primaryColor.main}"
        child("div") {
            padding(1.em)
            minHeight = 50.vh
        }
    }
}