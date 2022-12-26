package com.example.ajoyibrisep.domain

import com.example.ajoyibrisep.data.db.dao.RecipeDB
import com.example.ajoyibrisep.data.db.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.random.Random

class RecipeRepositoryImp private constructor() : RecipeRepository {
    private val categoryDao = RecipeDB.getDatabase().categoryDao()
    private val mealDao = RecipeDB.getDatabase().mealDao()
    private var recommendedData: MealModel? = null

    companion object {
        private var instance: RecipeRepository? = null
        fun getInstance(): RecipeRepository {
            if (instance == null) instance = RecipeRepositoryImp()
            return instance!!
        }
    }

    override fun getAllCategories() = flow<List<CategoryModel>> {
        emit(categoryDao.getAllCategories())
    }.flowOn(Dispatchers.IO)

    override fun getCategoryById(categoryId: Int) = flow<CategoryModel> {
        emit(categoryDao.getCategoryById(categoryId))
    }.flowOn(Dispatchers.IO)

    override fun getAllMeals() = flow<List<MealModel>> {
        emit(mealDao.getAllMeals())
    }.flowOn(Dispatchers.IO)

    override fun getMealById(mealId: Int) = flow<MealModel> {
        emit(mealDao.getMealById(mealId))
    }.flowOn(Dispatchers.IO)

    override fun getMealsByCategory(categoryId: Int) = flow<List<MealModel>> {
        emit(mealDao.getMealsByCategory(categoryId))
    }.flowOn(Dispatchers.IO)

    override fun getIngredientsById(id: Int) = flow<IngredientsModel> {
        emit(mealDao.getIngredientsById(id))
    }.flowOn(Dispatchers.IO)

    override fun getPreparationsById(id: Int) = flow<PreparationModel> {
        emit(mealDao.getPreparationById(id))
    }.flowOn(Dispatchers.IO)

    override fun getImagesById(id: Int) = flow<ImagesModel> {
        emit(mealDao.getImagesById(id))
    }.flowOn(Dispatchers.IO)

    override fun getRecommendedMeal() = flow<MealModel> {
        getAllMeals().collect {
            val index = Random.nextInt(0, it.size)
            if (recommendedData == null)
                recommendedData = it[index]
        }
        emit(recommendedData as MealModel)
    }.flowOn(Dispatchers.IO)
}