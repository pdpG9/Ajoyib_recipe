package com.example.ajoyibrisep.presentation.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import com.example.ajoyibrisep.data.model.IngredientsData

interface MealViewModel {
    fun clickBack()
    fun clickLink()
    fun loadCurrentMeal(mealId:Int)
    fun clickYouTube()
    fun clickIngredients()
    fun clickActions()

    val showProgressLiveData:LiveData<Boolean>
    val imagesLiveData:LiveData<List<String>>
    val titleLiveData:LiveData<String>
    val actionsLiveData:LiveData<IngredientsData>
    val moveToYouTubeLiveData:LiveData<Intent>
    val moveToWebLiveData:LiveData<Intent>
    val moveToBackLiveData:LiveData<Unit>
    val clickIngredientsLiveData:LiveData<Boolean>
    val stateYouTubeButtonLiveData:LiveData<Boolean>
}