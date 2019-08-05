package tz.co.asoft.ui.lifecycle

import android.content.Intent

data class ResultData(val requestCode: Int, val resultCode: Int, val data: Intent?, val done: () -> Unit)