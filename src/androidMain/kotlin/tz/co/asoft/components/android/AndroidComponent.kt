package tz.co.asoft.components.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState
import tz.co.asoft.persist.result.catching
import tz.co.asoft.ui.R
import kotlin.coroutines.CoroutineContext

open class AndroidComponent<P : CProps, S : CState> : Fragment(), Parent, CoroutineScope {
    private val job by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    protected lateinit var props: P

    protected lateinit var state: S

    open val layoutId by lazy { R.layout.fragment_frame }
    override val frameId by lazy { R.id.frame }

    override val fManager get() = childFragmentManager

    val application get() = activity?.application as? ComponentApplication

    fun initProps(p: P?) {
        p?.let {
            props = it
            onReceiveProps(p)
        }
    }

    open fun onReceiveProps(props: P) {

    }

    val attrs get() = props

    fun attrs(builder: P.() -> Unit) = props.apply(builder)

    private val propsKey get() = arguments?.getString("props")

    private val backDispatcher by lazy { requireActivity().onBackPressedDispatcher }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPropsFromArguments()
        savedInstanceState?.let { loadPropsAndState() }
    }

    private fun setPropsFromArguments() {
        val p = application?.props?.get(propsKey!!) as? P
        if (p != null) {
            props = p
        } else {
            application?.reboot()
        }
    }

    private fun loadPropsAndState() {
        application?.props?.get(propsKey!!)?.let { p -> props = p as P }
        application?.state?.get(propsKey!!)?.let { s -> state = s as S }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return (view ?: inflater.inflate(layoutId, container, false)).also {
            if (savedInstanceState == null) {
                executeRender()
            }
            backDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (onBackPressed()) {
                        backDispatcher.onBackPressed()
                    }
                }
            })
        }
    }

    protected open fun setState(builder: S.() -> Unit) {
        state.apply(builder)
        executeRender()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val p = catching { props }.data ?: CProps()
        val s = catching { state }.data ?: CState()
        application!!.props[propsKey!!] = p
        application!!.state[propsKey!!] = s
        super.onSaveInstanceState(outState)
    }

    protected fun executeRender() = launch(Dispatchers.Main) {
        render()
    }

    open fun render(): Any = Unit

    override fun onDestroy() {
        job.complete()
        super.onDestroy()
    }

    open fun onBackPressed(): Boolean = false
}
