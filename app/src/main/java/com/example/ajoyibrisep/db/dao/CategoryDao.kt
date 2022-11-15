package com.example.ajoyibrisep.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.ajoyibrisep.db.entity.CategoryModel

@Dao
interface CategoryDao {
    @Query("select * from category")
    suspend fun getAllCategories():List<CategoryModel>

    @Query("select * from category where id = :categoryId limit 1")
    suspend fun getCategoryById(categoryId:Int):CategoryModel
}