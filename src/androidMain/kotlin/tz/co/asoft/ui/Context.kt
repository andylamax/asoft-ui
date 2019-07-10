package tz.co.asoft.ui

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun Context.alert(msg: Any?) = GlobalScope.launch(Dispatchers.Main) {
    Toast.makeText(this@alert, msg.toString(), Toast.LENGTH_LONG).show()
}