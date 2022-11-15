package com.example.ajoyibrisep.adapter.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.adapter.recipe.view_holder.*
import com.example.ajoyibrisep.model.RecipeData

class RecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {
    private lateinit var list: List<RecipeData>
    private lateinit var actionBarLogoClickListener: (() -> Unit)
    private lateinit var recommendedMealClickListener: (() -> Unit)
    private lateinit var categoryClickListener: ((Int) -> Unit)
    private lateinit var mealsItemClickListener: ((Int) -> Unit)
    private lateinit var mealsItemLikeClickListener: ((Int) -> Unit)

    fun setActionBarClickListener(listener: () -> Unit) {
        actionBarLogoClickListener = listener
    }

    fun recommendedClickListener(listener: () -> Unit) {
        recommendedMealClickListener = listener
    }

    fun categoryClickListener(listener: (Int) -> Unit) {
        categoryClickListener = listener
    }

    fun mealsItemClickListener(listener: (Int) -> Unit) {
        mealsItemClickListener = listener
    }
    fun mealsItemLikeClickListener(listener: (Int) -> Unit) {
        mealsItemLikeClickListener = listener
    }
    fun submitList(l: List<RecipeData>) {
        list = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return when (viewType) {
            ActionBarViewType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main_top, parent, false)
                ActionBarTypeViewHolder(view, actionBarLogoClickListener)
            }
            RecommendedType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main_recommended, parent, false)
                RecommendedTypeViewHolder(view, recommendedMealClickListener)
            }
            CategoryType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main_category, parent, false)
                CategoryTypeViewHolder(view, categoryClickListener)
            }
            MealsType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main_recipe_list, parent, false)
                MealsTypeViewHolder(view, mealsItemClickListener,mealsItemLikeClickListener)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main_recipe_list, parent, false)
                MealsTypeViewHolder(view, mealsItemClickListener,mealsItemLikeClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size
    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is RecipeData.MainActionBarData -> ActionBarViewType
            is RecipeData.MainRecipeItemData -> MealsType
            is RecipeData.MainCategoryData -> CategoryType
            is RecipeData.MainRecommendedData -> RecommendedType
        }

    }

    companion object {
        const val ActionBarViewType = 0
        const val CategoryType = 1
        const val RecommendedType = 2
        const val MealsType = 3
    }


}