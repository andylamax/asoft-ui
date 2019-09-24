package tz.co.asoft.components

import tz.co.asoft.ui.lifecycle.ResultData
import tz.co.asoft.ui.theme.Theme

actual open class CProps actual constructor() {
    var resultData: ResultData? = null
    var theme = Theme()
}