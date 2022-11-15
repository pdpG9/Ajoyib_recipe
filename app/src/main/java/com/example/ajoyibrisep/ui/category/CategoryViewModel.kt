package com.example.ajoyibrisep.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajoyibrisep.db.dao.RecipeDB
import com.example.ajoyibrisep.db.entity.MealModel

class CategoryViewModel : ViewModel() {
    private val mealDao = RecipeDB.getDatabase().mealDao()
    private val _productList = MutableLiveData<List<MealModel>>()
    val productList: LiveData<List<MealModel>> = _productList
    private val _isShowPlaceHolder = MutableLiveData<Boolean>()
    val isShowPlaceHolder: LiveData<Boolean> = _isShowPlaceHolder


    suspend fun loadList(categoryId: Int) {
        val list = mealDao.getMealsByCategory(categoryId)
        _productList.postValue(list)
        if (list.isEmpty()){
        _isShowPlaceHolder.postValue(true)
        }else _isShowPlaceHolder.postValue(false)
    }
}