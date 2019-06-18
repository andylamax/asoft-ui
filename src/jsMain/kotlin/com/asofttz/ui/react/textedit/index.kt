@file:JsModule("react-draft-wysiwyg")
package com.asofttz.ui.react.textedit

import com.asofttz.theme.Theme
import react.Component
import react.RProps
import react.RState

external class Editor : Component<EditorProps, RState> {
    override fun render(): dynamic
}