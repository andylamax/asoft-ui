package tz.co.asoft.components

import tz.co.asoft.auth.User
import tz.co.asoft.components.TestComponent.State as State
import tz.co.asoft.components.TestComponent.Props as Props

class TestComponent : Component<Props, State>() {
    class Props : CProps()
    class State : CState() {
        var users = mutableListOf<User>()
    }

    override fun onReady() {
        super.onReady()
    }
}