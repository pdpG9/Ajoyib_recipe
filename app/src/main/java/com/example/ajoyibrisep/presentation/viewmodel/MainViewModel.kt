package com.example.ajoyibrisep.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.data.db.entity.MealModel

interface MainViewModel {
    fun loadData()
    fun clickItemMeal(recipeId: Int)
    fun clickItemCategory(categoryId: Int)
    fun clickItemRecommended()
    fun clickLogoButton()

    val categoriesLiveData: LiveData<List<CategoryModel>>
    val mealsLiveData: LiveData<List<MealModel>>
    val recommendedLiveData: LiveData<MealModel>
    val showProgressLiveData: LiveData<Boolean>
    val moveToInfoScreenLiveData: LiveData<Unit>
    val moveToMealScreenLiveData: LiveData<Int>
}