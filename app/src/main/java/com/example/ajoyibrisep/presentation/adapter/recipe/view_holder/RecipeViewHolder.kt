package com.example.ajoyibrisep.presentation.adapter.recipe.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecipeViewHolder(view:View):RecyclerView.ViewHolder(view){
    abstract fun bind()
}