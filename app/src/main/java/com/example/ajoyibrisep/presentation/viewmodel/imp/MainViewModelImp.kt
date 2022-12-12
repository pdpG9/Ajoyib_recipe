package com.example.ajoyibrisep.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.domain.RecipeRepositoryImp
import com.example.ajoyibrisep.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainViewModelImp : ViewModel(), MainViewModel {
    private val repository = RecipeRepositoryImp.getInstance()
    override fun loadData() {
        viewModelScope.launch {
            showProgressLiveData.value = true
            repository.getAllCategories().collect { categoriesLiveData.value = it }
            repository.getRecommendedMeal().collect { recommendedLiveData.value = it }
            repository.getAllMeals().collect { mealsLiveData.value = it }
            showProgressLiveData.value = false
        }
    }

    override fun clickItemMeal(recipeId: Int) {
        moveToMealScreenLiveData.value = recipeId
    }

    override fun clickItemCategory(categoryId: Int) {
        viewModelScope.launch {
            showProgressLiveData.value = true
            repository.getMealsByCategory(categoryId).collect {
                mealsLiveData.value = it
                showProgressLiveData.value = false
            }
        }
    }

    override fun clickItemRecommended() {
        viewModelScope.launch { repository.getRecommendedMeal().collect { moveToMealScreenLiveData.value = it.id } }
    }

    override fun clickLogoButton() {
        moveToInfoScreenLiveData.value = Unit
    }

    override val categoriesLiveData = MutableLiveData<List<CategoryModel>>()
    override val mealsLiveData = MutableLiveData<List<MealModel>>()
    override val recommendedLiveData = MutableLiveData<MealModel>()
    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val moveToInfoScreenLiveData = MutableLiveData<Unit>()
    override val moveToMealScreenLiveData = MutableLiveData<Int>()
}