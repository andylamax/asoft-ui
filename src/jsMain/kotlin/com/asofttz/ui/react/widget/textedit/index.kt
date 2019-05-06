@file:JsModule("react-draft-wysiwyg")
package com.asofttz.ui.react.widget.textedit

import com.asofttz.theme.Theme
import react.Component
import react.RProps
import react.RState

external interface EditorProps : RProps {
    var theme: Theme
    var editorState: dynamic //={editorState}
    var toolbarClassName: String //="toolbarClassName"
    var wrapperClassName: String //="wrapperClassName"
    var editorClassName: String //="editorClassName"
    var onEditorStateChange: () -> Unit //={this.onEditorStateChange}
}

external class Editor : Component<EditorProps, RState> {
    override fun render(): dynamic
}