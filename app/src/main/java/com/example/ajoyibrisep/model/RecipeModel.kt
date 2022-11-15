package com.example.ajoyibrisep.model

data class RecipeModel(
    val id:Int,
    val title:String,
    val description:String,
    val image:String,
    val rating:Int,
    val time:Int,
    val imageList:List<String>?,
    val webLink:String?,
    val videoLink:String?,
    val isFavourite:Boolean = false
)
