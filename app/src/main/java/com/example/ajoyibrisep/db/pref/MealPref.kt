package com.example.ajoyibrisep.db.pref

import android.content.Context
import android.content.SharedPreferences

class MealPref {
    companion object {
        private lateinit var pref: SharedPreferences
        fun init(context: Context) {
            pref = context.getSharedPreferences("like", Context.MODE_PRIVATE)
        }

        fun getPref() = pref

    }

    fun addMeal(id: Int): Boolean {
        return if (isHaveMeal(id)) {
            false
        } else {
            pref.edit().putInt("${id}-meal", id).apply()
            true
        }
    }

    fun removeMeal(id: Int): Boolean {
        if (isHaveMeal(id)) {
            pref.edit().putInt("${id}-meal", -1).apply()
            return true
        }
        return false
    }

    fun isHaveMeal(id: Int): Boolean {
        val mealId = pref.getInt("${id}-meal", -1)
        return mealId != -1
    }
}