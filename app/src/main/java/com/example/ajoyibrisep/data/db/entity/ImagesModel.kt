package com.example.ajoyibrisep.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImagesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image1: String?,
    val image2: String?,
    val image3: String?,
    val image4: String?,
    val image5: String?
)
