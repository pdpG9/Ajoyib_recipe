package com.example.ajoyibrisep.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.databinding.ItemMainRecipeListBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class HomeRecipeListController : BindableItemController<List<MealModel>, HomeRecipeListController.Holder>() {
    private lateinit var itemClickListener: ((Int) -> Unit)
    fun setListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }
    inner class Holder(val binding: ItemMainRecipeListBinding) : BindableViewHolder<List<MealModel>>(binding.root) {
        private val adapter = MealsAdapter()

        init {
            binding.rvRecipe.adapter = adapter
            binding.rvRecipe.layoutManager = GridLayoutManager(itemView.context, 2)
            adapter.setListener(itemClickListener)
        }

        override fun bind(data: List<MealModel>?) {
            adapter.submitList(data)
        }

    }

    override fun createViewHolder(parent: ViewGroup?) = Holder(ItemMainRecipeListBinding.inflate(LayoutInflater.from(parent?.context), parent, false))

    override fun getItemId(data: List<MealModel>?) = data?.size?:0

}