package tz.co.asoft.ui.samples.jfx.di

import tz.co.asoft.persist.di.single
import tz.co.asoft.ui.samples.jfx.MainControlledComponentViewModel

object injection {
    object viewModel {
        fun mainControlledComponent() = single { MainControlledComponentViewModel() }
    }
}