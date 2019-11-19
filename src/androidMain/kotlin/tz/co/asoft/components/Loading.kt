package tz.co.asoft.components

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import tz.co.asoft.ui.R
import tz.co.asoft.ui.ViewHolder
import tz.co.asoft.ui.theme.main

class Loading : Component<Loading.Props, CState>() {
    class Props : CProps() {
        var msg = ""
    }

    init {
        props = Props()
    }

    override val layoutId = R.layout.loading

    class VH(view: View?) : ViewHolder(view) {
        val progressBar: ProgressBar? by Id(R.id.progress_bar)
        val loadingText: TextView? by Id(R.id.loading_text)
    }

    override fun onReady() {
        super.onReady()
        VH(view).bindUI()
    }

    private fun VH.bindUI() {
        loadingText?.text = props.msg
    }
}