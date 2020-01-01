package tz.co.asoft.viewmodel

import tz.co.asoft.rx.LiveData
import androidx.lifecycle.ViewModel as LifecycleViewModel

actual abstract class ViewModel<I, S> actual constructor(initialState: S) : LifecycleViewModel() {
    actual val ui = LiveData(initialState)
    actual abstract suspend fun post(i: I)
}