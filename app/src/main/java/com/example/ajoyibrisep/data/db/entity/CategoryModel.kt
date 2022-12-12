package com.example.ajoyibrisep.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "category")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: String?
)