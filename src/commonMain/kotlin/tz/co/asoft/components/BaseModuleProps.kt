package tz.co.asoft.components

import tz.co.asoft.auth.User

abstract class BaseModuleProps : CProps() {
    var user = User.fake
}