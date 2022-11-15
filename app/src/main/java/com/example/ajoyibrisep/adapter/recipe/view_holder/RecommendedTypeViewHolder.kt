package com.example.ajoyibrisep.adapter.recipe.view_holder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.model.RecipeData
import de.hdodenhof.circleimageview.CircleImageView

class RecommendedTypeViewHolder(view: View, listener: () -> Unit) :
    RecipeViewHolder(view) {
    private val recImageView: CircleImageView = view.findViewById(R.id.iv_todayItem)
    private val recTitle: AppCompatTextView = view.findViewById(R.id.tv_todayItemTitle)
    private val recDescription: AppCompatTextView = view.findViewById(R.id.tv_today_description)

    init {
        itemView.setOnClickListener {
            listener.invoke()
        }
    }

    override fun bind(data: RecipeData) {
        val temp = (data as RecipeData.MainRecommendedData).data
        Glide.with(itemView)
            .load(temp.image)
            .placeholder(R.drawable.image_menu)
            .into(recImageView)
        recTitle.text = temp.title
        recDescription.text = temp.description
    }

}