package com.example.ajoyibrisep.domain

import com.example.ajoyibrisep.data.db.entity.*
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAllCategories(): Flow<List<CategoryModel>>
    fun getCategoryById(categoryId: Int): Flow<CategoryModel>
    fun getAllMeals(): Flow<List<MealModel>>
    fun getMealById(mealId: Int): Flow<MealModel>
    fun getMealsByCategory(categoryId: Int): Flow<List<MealModel>>
    fun getIngredientsById(id: Int): Flow<IngredientsModel>
    fun getPreparationsById(id: Int): Flow<PreparationModel>
    fun getImagesById(id: Int): Flow<ImagesModel>
    fun getRecommendedMeal(): Flow<MealModel>
}