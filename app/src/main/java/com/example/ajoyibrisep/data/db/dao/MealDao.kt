package com.example.ajoyibrisep.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.ajoyibrisep.data.db.entity.ImagesModel
import com.example.ajoyibrisep.data.db.entity.IngredientsModel
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.data.db.entity.PreparationModel

@Dao
interface MealDao {
    @Query("select * from meal")
    suspend fun getAllMeals(): List<MealModel>

    @Query("select * from meal where id = :mealId limit 1")
    suspend fun getMealById(mealId: Int): MealModel

    @Query("select * from meal where category = :categoryId")
    suspend fun getMealsByCategory(categoryId: Int): List<MealModel>

    @Query("select * from ingredients where id = :id limit 1")
    suspend fun getIngredientsById(id: Int): IngredientsModel

    @Query("select * from preparation where id = :id limit 1")
    suspend fun getPreparationById(id: Int): PreparationModel

    @Query("select * from images where id = :id limit 1")
    suspend fun getImagesById(id: Int): ImagesModel

}