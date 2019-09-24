package tz.co.asoft.ui.state

sealed class UI {
    class Loading(val msg: String = "Loading . . .") : UI()
    open class Showing : UI()
    class Error(val msg: String = "Unknown Error") : UI()
}