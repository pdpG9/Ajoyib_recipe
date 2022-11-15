package com.example.ajoyibrisep.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val preparation: Int,
    val ingredients: Int,
    val time: Int,
    val category: Int,
    val rating: String,
    val web_url: String?,
    val video_uri: String?,
    val images: Int?
):java.io.Serializable