package tz.co.asoft.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class ComponentFragmentActivity<T : Any> : FragmentActivity() {
    lateinit var state: T
    protected inline fun ComponentFragmentActivity<T>.setState(builder: T.() -> Unit) {
        state.apply(builder)
        render()
    }

    override fun onStart() {
        super.onStart()
        render()
    }

    open fun render(): Any = Unit

    protected fun Fragment.showFragment(id: Int, backStack: String? = null) = showFragment(id, this, backStack)
    protected fun showFragment(id: Int, fragment: Fragment, backStack: String? = null) = supportFragmentManager.showFragment(id, fragment, backStack)

    private fun onBackPressed(fm: FragmentManager?): Boolean {
        if (fm != null) {
            if (fm.backStackEntryCount > 0) {
                fm.popBackStack()
                return true
            }

            val fragList = fm.fragments
            if (fragList.size > 0) {
                for (frag in fragList) {
                    if (frag == null) {
                        continue
                    }
                    if (frag.isVisible) {
                        if (onBackPressed(frag.childFragmentManager)) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (onBackPressed(fm)) {
            return
        }
        super.onBackPressed()
    }
}

abstract class ScopedFragmentActivity<S : Any> : ComponentFragmentActivity<S>(), CoroutineScope {
    protected var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected fun ScopedFragmentActivity<S>.syncState(coroutineContext: CoroutineContext = job, builder: suspend S.() -> Unit) {
        launch(coroutineContext) {
            state.apply {
                builder()
            }
            withContext(Dispatchers.Main) { setState { } }
        }
    }

    override fun onResume() {
        super.onResume()
        job = Job()
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}