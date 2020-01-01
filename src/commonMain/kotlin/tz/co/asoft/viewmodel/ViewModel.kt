package tz.co.asoft.viewmodel

import tz.co.asoft.rx.LiveData

expect abstract class ViewModel<I, S>(initialState: S) {
    val ui: LiveData<S>
    abstract suspend fun post(i: I)
}