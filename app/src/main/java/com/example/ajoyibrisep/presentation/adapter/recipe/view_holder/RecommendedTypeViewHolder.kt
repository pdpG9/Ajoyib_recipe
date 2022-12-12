package com.example.ajoyibrisep.presentation.adapter.recipe.view_holder

import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.databinding.ItemMainRecommendedBinding
import de.hdodenhof.circleimageview.CircleImageView

class RecommendedTypeViewHolder(binding: ItemMainRecommendedBinding, listener: () -> Unit) :
    RecipeViewHolder(binding.root) {
    private var recommendedMeal: MealModel? = null
    private val recImageView: CircleImageView = binding.ivTodayItem
    private val recTitle: AppCompatTextView = binding.tvTodayItemTitle
    private val recDescription: AppCompatTextView = binding.tvTodayDescription

    init { itemView.setOnClickListener { listener.invoke() } }
    fun setMeal(data: MealModel) {
        recommendedMeal = data
        bind()
    }
    override fun bind() {
        recommendedMeal?.let { data->
        Glide.with(itemView)
            .load(data.image)
            .placeholder(R.drawable.image_menu)
            .into(recImageView)
        recTitle.text = data.title
        recDescription.text = data.description
        } }
}