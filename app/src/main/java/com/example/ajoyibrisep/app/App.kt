package com.example.ajoyibrisep.app

import android.app.Application
import com.example.ajoyibrisep.db.dao.RecipeDB
import com.example.ajoyibrisep.db.pref.MealPref
import org.greenrobot.eventbus.EventBus

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        RecipeDB.init(this)
        MealPref.init(this)
        EventBus.builder().installDefaultEventBus()
    }
}