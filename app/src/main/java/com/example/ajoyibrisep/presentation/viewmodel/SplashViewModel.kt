package com.example.ajoyibrisep.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    fun clickAction()
    val moveToMainScreen:LiveData<Unit>
}