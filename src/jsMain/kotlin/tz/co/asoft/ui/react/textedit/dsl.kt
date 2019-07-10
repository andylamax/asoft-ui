package tz.co.asoft.ui.react.textedit

import tz.co.asoft.ui.theme.Theme
import kotlinext.js.jsObject
import kotlinext.js.require
import kotlinx.css.*
import react.RBuilder
import react.RHandler
import react.RProps
import styled.css
import styled.styledDiv

var isTextEditorCssLoaded = false

interface EditorProps : RProps {
    var theme: Theme
    var css: CSSBuilder.()->Unit
    var editorState: dynamic //={editorState}
    var toolbarClassName: String //="toolbarClassName"
    var wrapperClassName: String //="wrapperClassName"
    var editorClassName: String //="editorClassName"
    var onEditorStateChange: () -> Unit //={this.onEditorStateChange}
}

fun RBuilder.textEditor(handler: RHandler<EditorProps>) = styledDiv {
    var props = jsObject<EditorProps> {
        css = {}
    }
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
        border = "solid 2px ${props.theme.primaryColor.main}"

        child("div") {
            padding(1.em)
            minHeight = 50.vh
        }
        +props.css
    }
}