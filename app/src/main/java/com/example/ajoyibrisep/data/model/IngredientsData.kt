package com.example.ajoyibrisep.data.model

import com.example.ajoyibrisep.data.db.entity.IngredientsModel
import com.example.ajoyibrisep.data.db.entity.PreparationModel

data class IngredientsData(
    val ingredients:IngredientsModel,
    val preparation:PreparationModel
)