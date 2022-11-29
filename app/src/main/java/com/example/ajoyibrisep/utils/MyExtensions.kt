package com.example.ajoyibrisep.utils

import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager

fun String.myLogD() {
    Log.d("TTT", this)
}
const val CATEGORY_DATA = "category"
const val MEAl_DATA = "meal"
fun Window.changeColorByMode(isDarkMode: Boolean, color: Int) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val statusBars = WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        this.decorView.windowInsetsController?.setSystemBarsAppearance(if (isDarkMode) 0 else statusBars, statusBars)
        return
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.decorView.systemUiVisibility = if (!isDarkMode) View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
        this.decorView.systemUiVisibility = if (!isDarkMode) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
        return
    } else {
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = color
    }
}