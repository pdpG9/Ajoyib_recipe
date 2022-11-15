package com.example.ajoyibrisep.adapter.recipe.view_holder

import android.view.View
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.model.RecipeData
import de.hdodenhof.circleimageview.CircleImageView

class ActionBarTypeViewHolder(view: View, private val listener: () -> Unit) :
    RecipeViewHolder(view) {
    private val btLogo: CircleImageView = view.findViewById(R.id.bt_logo)

    init {
        btLogo.setOnClickListener {
            listener.invoke()
        }

    }
    override fun bind(data: RecipeData) {
    }
}