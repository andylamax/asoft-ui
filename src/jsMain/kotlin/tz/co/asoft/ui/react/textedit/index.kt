@file:JsModule("react-draft-wysiwyg")
package tz.co.asoft.ui.react.textedit

import react.Component
import react.RState

external class Editor : Component<EditorProps, RState> {
    override fun render(): dynamic
}