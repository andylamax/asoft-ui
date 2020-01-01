package tz.co.asoft.ui.samples.app

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import tz.co.asoft.component.Component
import tz.co.asoft.ui.ViewHolder

class Splash : Component<Splash.Props, Any>() {
    override val layoutId = R.layout.splash

    class Props {
        var text = ""
    }

    var handler: Handler? = null

    interface Handler {
        fun onSplashTextChanged(txt: String)
    }

    class VH(v: View?) : ViewHolder(v) {
        val textView: TextView? by Id(R.id.tv)
        val et: EditText? by Id(R.id.editText)
        val btn: Button? by Id(R.id.button)
    }

    override fun onResume() {
        super.onResume()
        VH(view).bindUI()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val p = parentFragment
        if (p is Handler) {
            handler = p
        }
    }

    private fun VH.bindUI() {
        textView?.text = props.text
        btn?.setOnClickListener { et?.text?.toString()?.let { handler?.onSplashTextChanged(it) } }
    }
}