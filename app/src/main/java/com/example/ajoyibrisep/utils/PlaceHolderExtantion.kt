package com.example.ajoyibrisep.utils

import android.util.Log
import android.view.View
import android.widget.ProgressBar

fun ProgressBar.showProgress(b: Boolean) {
    if (b) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}
fun String.myLog() {
    Log.d("TTT", "myLog: $this")
}
