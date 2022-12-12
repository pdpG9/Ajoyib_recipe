package com.example.ajoyibrisep.presentation.adapter.recipe

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.databinding.ItemMainCategoryBinding
import com.example.ajoyibrisep.databinding.ItemMainRecipeListBinding
import com.example.ajoyibrisep.databinding.ItemMainRecommendedBinding
import com.example.ajoyibrisep.presentation.adapter.recipe.view_holder.CategoryTypeViewHolder
import com.example.ajoyibrisep.presentation.adapter.recipe.view_holder.MealsTypeViewHolder
import com.example.ajoyibrisep.presentation.adapter.recipe.view_holder.RecipeViewHolder
import com.example.ajoyibrisep.presentation.adapter.recipe.view_holder.RecommendedTypeViewHolder
import com.example.ajoyibrisep.utils.myInflate

class RecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {
    private lateinit var recommendedMealClickListener: (() -> Unit)
    private lateinit var categoryClickListener: ((Int) -> Unit)
    private lateinit var mealsItemClickListener: ((Int) -> Unit)
    private lateinit var actionBarLogoClickListener: (() -> Unit)

    private var categoryVH: CategoryTypeViewHolder? = null
    private var mealsVH: MealsTypeViewHolder? = null
    private var recommendedVH: RecommendedTypeViewHolder? = null

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

    fun setMealsList(list: List<MealModel>){
        mealsVH?.submitList(list)
    }
    fun setCategoryList(list: List<CategoryModel>){
        categoryVH?.submitList(list)
    }
    fun setRecommendedMeal(meal:MealModel){
        recommendedVH?.setMeal(meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return when (viewType) {
            RecommendedType -> {
                    recommendedVH = RecommendedTypeViewHolder(
                        ItemMainRecommendedBinding.bind(parent.myInflate(R.layout.item_main_recommended)),
                        recommendedMealClickListener)
                recommendedVH!!
            }
            CategoryType -> {
                    categoryVH =
                        CategoryTypeViewHolder(ItemMainCategoryBinding.bind(parent.myInflate(R.layout.item_main_category)), categoryClickListener)
                categoryVH!!
            }
            MealsType -> {
                    mealsVH =
                        MealsTypeViewHolder(ItemMainRecipeListBinding.bind(parent.myInflate(R.layout.item_main_recipe_list)), mealsItemClickListener)
                mealsVH!!
            }
            else -> {
                    mealsVH = MealsTypeViewHolder(
                        ItemMainRecipeListBinding.bind(parent.myInflate(R.layout.item_main_recipe_list)),
                        mealsItemClickListener)
                mealsVH!!
            }
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind()

    override fun getItemCount() = 3
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> CategoryType
            2 -> MealsType
            1 -> RecommendedType
            else -> {
                MealsType
            }
        }

    }

    companion object {
        const val CategoryType = 0
        const val RecommendedType = 1
        const val MealsType = 2
    }


}