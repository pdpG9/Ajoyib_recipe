package com.example.ajoyibrisep.data.db.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ajoyibrisep.data.db.entity.*

@Database(
    entities = [CategoryModel::class, MealModel::class, ImagesModel::class, IngredientsModel::class, PreparationModel::class],
    version = 1
)
abstract class RecipeDB : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao

    companion object{
        private var INSTANCE: RecipeDB? = null
        private const val DB_NAME = "recipe.db"
        fun init(context: Context){
            if (INSTANCE ==null){
                synchronized(RecipeDB::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RecipeDB::class.java, DB_NAME)
                        .createFromAsset("recipe.db")
                        .build()
                }
            }
        }

        fun getDatabase(): RecipeDB {

            return INSTANCE!!
        }

    }
}