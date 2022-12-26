package com.example.ajoyibrisep.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.databinding.ItemMainRecommendedBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class HomeRecommendedController : BindableItemController<MealModel, HomeRecommendedController.Holder>() {
    private var recommendedItemClickListener: (() -> Unit)? = null

    fun setItemClickListener(block: () -> Unit) {
        recommendedItemClickListener = block
    }
    inner class Holder(val binding: ItemMainRecommendedBinding) : BindableViewHolder<MealModel>(binding.root) {
        init {
            itemView.setOnClickListener { recommendedItemClickListener?.invoke()}
        }
        override fun bind(data: MealModel?) {
            data?.let {
                Glide.with(binding.root).load(data.image).into(binding.ivTodayItem)
                binding.tvTodayItemTitle.text = data.title
                binding.tvTodayDescription.text = data.description
            }
        }

    }

    override fun createViewHolder(parent: ViewGroup?) =
        Holder(ItemMainRecommendedBinding.inflate(LayoutInflater.from(parent?.context), parent, false))

    override fun getItemId(data: MealModel?) = ID_TAG

    private companion object {
        const val ID_TAG = "HomeCategoryController"
    }
}