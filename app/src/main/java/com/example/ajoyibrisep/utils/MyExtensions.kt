package com.example.ajoyibrisep.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

fun ViewGroup.myInflate(resId: Int): View = LayoutInflater.from(this.context).inflate(resId, this, false)

@SuppressLint("ObsoleteSdkInt")
fun FragmentActivity.showStatusBar() {
    this.window.apply {
        decorView.systemUiVisibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val flags: Int = View.VISIBLE
            decorView.systemUiVisibility = flags
        }
        navigationBarColor = Color.TRANSPARENT
    }
}

@SuppressLint("ObsoleteSdkInt")
fun FragmentActivity.hideStatusBar() {
    this.window.apply {
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags: Int = decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = flags
        }

        navigationBarColor = Color.TRANSPARENT
    }
}

fun FragmentActivity.uploadStatusBarColor(context: Context) {
    val mode = context.resources.configuration.uiMode
    if (mode == 33) {
        this.window.statusBarColor = Color.BLACK
    } else {
        this.window.statusBarColor = Color.WHITE
    }
}