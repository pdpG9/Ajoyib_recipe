package com.example.ajoyibrisep.domain

import com.example.ajoyibrisep.data.db.dao.RecipeDB
import com.example.ajoyibrisep.data.db.entity.*
import kotlinx.coroutines.flow.flow
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
    }

    override fun getCategoryById(categoryId: Int) = flow<CategoryModel> {
        emit(categoryDao.getCategoryById(categoryId))
    }

    override fun getAllMeals() = flow<List<MealModel>> {
        emit(mealDao.getAllMeals())
    }

    override fun getMealById(mealId: Int) = flow<MealModel> {
        emit(mealDao.getMealById(mealId))
    }

    override fun getMealsByCategory(categoryId: Int) = flow<List<MealModel>> {
        emit(mealDao.getMealsByCategory(categoryId))
    }

    override fun getIngredientsById(id: Int) = flow<IngredientsModel> {
        emit(mealDao.getIngredientsById(id))
    }

    override fun getPreparationsById(id: Int) = flow<PreparationModel> {
        emit(mealDao.getPreparationById(id))
    }

    override fun getImagesById(id: Int) = flow<ImagesModel> {
        emit(mealDao.getImagesById(id))
    }

    override fun getRecommendedMeal() = flow<MealModel> {
        getAllMeals().collect {
            val index = Random.nextInt(0, it.size)
            if (recommendedData == null)
                recommendedData = it[index]
        }
        emit(recommendedData as MealModel)
    }
}