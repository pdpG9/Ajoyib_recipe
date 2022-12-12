package com.example.ajoyibrisep.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preparation")
data class PreparationModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val action1: String?,
    val action2: String?,
    val action3: String?,
    val action4: String?,
    val action5: String?,
    val action6: String?,
    val action7: String?,
    val action8: String?,
    val action9: String?,
    val action10: String?,
    val action11: String?,
    val action12: String?,
    val action13: String?,
    val action14: String?,
    val action15: String?
)
