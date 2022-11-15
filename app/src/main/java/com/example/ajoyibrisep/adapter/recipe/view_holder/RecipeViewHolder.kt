package com.example.ajoyibrisep.adapter.recipe.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.model.RecipeData

abstract class RecipeViewHolder(view:View):RecyclerView.ViewHolder(view){
    abstract fun bind(data: RecipeData)
}