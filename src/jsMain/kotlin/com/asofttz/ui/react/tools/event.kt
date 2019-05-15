package com.asofttz.ui.react.tools

import org.w3c.dom.events.EventTarget

val EventTarget?.value: String get() = asDynamic().value.toString()