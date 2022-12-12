package com.example.ajoyibrisep.presentation.adapter.recipe.view_holder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.databinding.ItemMainRecipeListBinding
import com.example.ajoyibrisep.presentation.adapter.MealsAdapter
import com.example.ajoyibrisep.utils.myLog

class MealsTypeViewHolder(
    binding: ItemMainRecipeListBinding,
    private val listener: (Int) -> Unit
) :
    RecipeViewHolder(binding.root) {
    private val rvMealsList: RecyclerView = binding.rvRecipe
    private var mealsList: List<MealModel>? = null
    private val adapter = MealsAdapter()

    fun submitList(list: List<MealModel>) {
        mealsList = list
        bind()
    }

    init {
        rvMealsList.layoutManager = GridLayoutManager(binding.root.context, 2)
        rvMealsList.adapter = adapter
        adapter.setListener { listener.invoke(it) }
    }

    override fun bind() {
        mealsList?.let { list ->
            "list size:${list.size}".myLog()
            adapter.submitList(list)
        }
    }
}