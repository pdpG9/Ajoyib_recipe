package com.example.ajoyibrisep.app

import android.app.Application
import com.example.ajoyibrisep.data.db.dao.RecipeDB
import com.example.ajoyibrisep.data.db.pref.MealPref

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        RecipeDB.init(this)
        MealPref.init(this)
    }
}