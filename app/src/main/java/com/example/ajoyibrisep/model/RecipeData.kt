package com.example.ajoyibrisep.model

import com.example.ajoyibrisep.db.entity.CategoryModel
import com.example.ajoyibrisep.db.entity.MealModel

sealed class RecipeData {
    data class MainActionBarData(val id: Int) : RecipeData()
    data class MainRecommendedData(val data: MealModel) : RecipeData()
    data class MainCategoryData(val data: List<CategoryModel>) : RecipeData()
    data class MainRecipeItemData(val recipeList: List<MealModel>) : RecipeData()
}
