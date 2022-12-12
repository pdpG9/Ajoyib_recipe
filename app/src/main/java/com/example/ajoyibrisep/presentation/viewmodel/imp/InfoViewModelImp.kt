package com.example.ajoyibrisep.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajoyibrisep.presentation.viewmodel.InfoViewModel

class InfoViewModelImp : ViewModel(), InfoViewModel {
    override fun clickBack() {
        moveToBackLiveData.value = Unit
    }

    override val moveToBackLiveData = MutableLiveData<Unit>()
}