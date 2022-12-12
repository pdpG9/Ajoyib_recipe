package com.example.ajoyibrisep.presentation.viewmodel

import androidx.lifecycle.LiveData

interface InfoViewModel {
    fun clickBack()
    val moveToBackLiveData:LiveData<Unit>
}