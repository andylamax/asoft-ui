package com.asofttz.ui.react.widget.text.searchbar

import com.asofttz.ui.react.tools.ThemedProps
import com.asofttz.ui.react.widget.text.searchbar.SearchBox.Props
import kotlinx.css.Color
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onKeyDownFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv
import styled.styledInput
import kotlin.browser.document

class SearchBox(p: Props) : RComponent<Props, RState>(p) {

    object Props : ThemedProps() {
        var hint = ""
        var onSearch = { _: String -> }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +SearchBarStyles.root
                +props.css
            }

            styledInput(type = InputType.text) {
                attrs {
                    id = "asoft-view-search-bar"
                    placeholder = "Search . . ."
                    onKeyDownFunction = {
                        val e = it.unsafeCast<KeyboardEvent>()
                        if (e.keyCode == 13) {
                            props.onSearch((document.getElementById(id) as HTMLInputElement).value)
                        }
                    }
                }

                css {
                    +SearchBarStyles.input
                    props.theme.let {
                        color = Color(it.text.onBackground.light)

                        focus {
                            borderBottomColor = Color(it.primaryColor.dark)
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.searchBox(handler: RHandler<Props> = {}) = child(SearchBox::class.js, Props) {
    attrs {
        handler()
    }
}