package tz.co.asoft.viewmodel

import tornadofx.ViewModel as FXViewModel
import tz.co.asoft.rx.LiveData

actual abstract class ViewModel<I, S> actual constructor(initialState: S) : FXViewModel() {
    actual val ui = LiveData(initialState)
    actual abstract suspend fun post(i: I)
}