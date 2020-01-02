package tz.co.asoft.component

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import react.RBuilder
import react.RProps
import react.RState
import react.setState
import tz.co.asoft.component.ControlledComponent.UIState
import tz.co.asoft.rx.LiveData
import tz.co.asoft.viewmodel.ViewModel

abstract class ControlledComponent<P : RProps, I, S, V : ViewModel<I, S>> : Component<P, UIState<S>> {
    abstract val viewModel: V

    class UIState<S> : RState {
        var ui: S? = null
    }

    constructor() : super()

    constructor(props: P) : super(props)

    init {
        state = UIState()
    }

    abstract fun RBuilder.render(ui: S)

    override fun RBuilder.render() {
        state.ui?.let { render(it) }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        viewModel.ui.bind()
    }

    fun LiveData<S>.bind() = observe { setState { ui = it } }

    fun Channel<S>.bind() = launch { consumeAsFlow().bind() }

    fun Flow<S>.bind() = launch { onEach { setState { ui = it } }.collect() }

    open fun post(i: I) = launch { viewModel.post(i) }
}