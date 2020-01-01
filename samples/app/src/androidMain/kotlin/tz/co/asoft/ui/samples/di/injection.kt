package tz.co.asoft.ui.samples.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import tz.co.asoft.ui.samples.MainComponentViewModel

object injection {
    object viewModel {
        fun mainComponent(a: FragmentActivity) = ViewModelProviders.of(a)[MainComponentViewModel::class.java]
    }
}