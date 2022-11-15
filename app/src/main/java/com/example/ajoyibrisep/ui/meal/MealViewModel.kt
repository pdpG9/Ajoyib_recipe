package com.example.ajoyibrisep.ui.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ajoyibrisep.db.dao.RecipeDB
import com.example.ajoyibrisep.db.entity.ImagesModel
import com.example.ajoyibrisep.db.entity.IngredientsModel
import com.example.ajoyibrisep.db.entity.PreparationModel

class MealViewModel : ViewModel() {
    private val mealDao = RecipeDB.getDatabase().mealDao()
    private val _imagesList = MutableLiveData<ImagesModel>()
    val imagesList: LiveData<ImagesModel> = _imagesList
    private val _ingredientsList = MutableLiveData<IngredientsModel>()
    private val _preparationList = MutableLiveData<PreparationModel>()
    private val _pagerData = MutableLiveData<Pair<IngredientsModel?, PreparationModel?>>()
    val pagerData: LiveData<Pair<IngredientsModel?, PreparationModel?>> = _pagerData

    suspend fun loadData(mealId: Int) {
        _imagesList.value = mealDao.getImagesById(mealId)

        _ingredientsList.value = mealDao.getIngredientsById(mealId)

        _preparationList.value = mealDao.getPreparationById(mealId)

        load()
    }

    private fun load() {
        _pagerData.value = Pair(_ingredientsList.value, _preparationList.value)
    }
}