package com.example.ajoyibrisep.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajoyibrisep.presentation.viewmodel.SplashViewModel

class SplashViewModelImp : ViewModel(), SplashViewModel {
    override fun clickAction() {
        moveToMainScreen.value = Unit
    }

    override val moveToMainScreen = MutableLiveData<Unit>()
}