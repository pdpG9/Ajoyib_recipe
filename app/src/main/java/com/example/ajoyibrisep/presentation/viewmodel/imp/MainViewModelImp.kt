package com.example.ajoyibrisep.presentation.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.domain.RecipeRepositoryImp
import com.example.ajoyibrisep.presentation.viewmodel.MainViewModel
import com.example.ajoyibrisep.utils.myLog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModelImp : ViewModel(), MainViewModel {
    private val repository = RecipeRepositoryImp.getInstance()
    override fun loadData() {
        showProgressLiveData.value = true
        viewModelScope.launch {
            repository.getAllCategories().collect {
                val l = ArrayList<CategoryModel>()
                l.add(CategoryModel(-1, "All", ""))
                l.addAll(it)
                categoriesLiveData.value = l
            }
            repository.getRecommendedMeal().collect { recommendedLiveData.value = it }
            repository.getAllMeals().collect { mealsLiveData.value = it }
            showProgressLiveData.value = false
        }
    }

    override fun clickItemMeal(recipeId: Int) {
        moveToMealScreenLiveData.value = recipeId
    }

    override fun clickItemCategory(categoryId: Int) {
        if (categoryId != -1) {
            showProgressLiveData.value = true
            repository.getMealsByCategory(categoryId).onEach {
                mealsLiveData.value = it
                "clickItemCategory: $categoryId".myLog()
                showProgressLiveData.value = false
            }.launchIn(viewModelScope)
        } else {
            repository.getAllMeals().onEach {
                mealsLiveData.value = it
                "clickItemCategory: $categoryId".myLog()
            }.launchIn(viewModelScope)
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