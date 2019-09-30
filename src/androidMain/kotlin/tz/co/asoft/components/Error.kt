package tz.co.asoft.components

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import tz.co.asoft.ui.R
import tz.co.asoft.ui.ViewHolder
import tz.co.asoft.ui.action.Action

class Error : Component<Error.Props, CState>() {
    class Props : CProps() {
        var msg = ""
        var actions = listOf<Action>()
    }

    override val layoutId = R.layout.error

    class VH(view: View) : ViewHolder(view) {
        val errorText: TextView by Id(R.id.error_text)
        val actions: LinearLayout by Id(R.id.actions)
    }

    override fun onReady() {
        super.onReady()
        VH(view!!).bindUI()
    }

    private fun Action.toButton() = Button(ctx).apply {
        text = name
        setOnClickListener { handler() }
    }

    private fun VH.bindUI() {
        errorText.text = props.msg
        actions.removeAllViews()
        props.actions.forEach { actions.addView(it.toButton()) }
    }
}