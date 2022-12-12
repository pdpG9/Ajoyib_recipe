package com.example.ajoyibrisep.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val product1: String,
    val product2: String?,
    val product3: String?,
    val product4: String?,
    val product5: String?,
    val product6: String?,
    val product7: String?,
    val product8: String?,
    val product9: String?,
    val product10: String?,
    val product11: String?,
    val product12: String?,
    val product13: String?,
    val product14: String?,
    val product15: String?
)
