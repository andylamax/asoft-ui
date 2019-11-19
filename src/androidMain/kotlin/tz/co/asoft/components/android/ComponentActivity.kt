package tz.co.asoft.components.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import tz.co.asoft.ui.R
import kotlin.coroutines.CoroutineContext

open class ComponentActivity : AppCompatActivity(), Parent, CoroutineScope {
    protected val job by lazy { Job() }
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    override val fManager get() = supportFragmentManager!!

    val ctx get() = applicationContext!!

    val app get() = application as? ComponentApplication

    open val layoutId by lazy { R.layout.fragment_frame }
    override val frameId by lazy { R.id.frame }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        if (savedInstanceState == null) {
            render()
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    open fun render(): Any = Unit
}