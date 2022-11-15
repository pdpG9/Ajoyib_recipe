package com.example.ajoyibrisep.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajoyibrisep.db.dao.RecipeDB
import com.example.ajoyibrisep.db.entity.CategoryModel
import com.example.ajoyibrisep.db.entity.MealModel

class MainViewModel : ViewModel() {
    private val _recommendedMeal = MutableLiveData<MealModel>()
    val recommendedMeal: LiveData<MealModel> = _recommendedMeal
    private val _categoryModel = MutableLiveData<List<CategoryModel>>()
    val categoryModel: LiveData<List<CategoryModel>> = _categoryModel
    private val _mealsModel = MutableLiveData<List<MealModel>>()
    val mealsModel: LiveData<List<MealModel>> = _mealsModel
    private val mealDao = RecipeDB.getDatabase().mealDao()
    private val categoryDao = RecipeDB.getDatabase().categoryDao()

    suspend fun loadData() {
        if (categoryModel.value.isNullOrEmpty()){
        _categoryModel.value = categoryDao.getAllCategories()
        _mealsModel.value = mealDao.getAllMeals()
        }
    }

    suspend fun loadRecommended() {
        val id = (1..mealsModel.value!!.size).random()
        _recommendedMeal.value = mealDao.getMealById(id)
    }


}