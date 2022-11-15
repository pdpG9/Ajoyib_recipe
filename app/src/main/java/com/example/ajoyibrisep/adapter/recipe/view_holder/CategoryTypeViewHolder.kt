package com.example.ajoyibrisep.adapter.recipe.view_holder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.adapter.CategoryAdapter
import com.example.ajoyibrisep.model.RecipeData

class CategoryTypeViewHolder(view: View, private val listener: (Int) -> Unit) :
    RecipeViewHolder(view) {
    private val rvCategory: RecyclerView = view.findViewById(R.id.rv_category)
    private lateinit var adapter: CategoryAdapter

    init {
        rvCategory.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun bind(data: RecipeData) {
        val temp = (data as RecipeData.MainCategoryData).data
        adapter = CategoryAdapter(temp, listener)
        rvCategory.adapter = adapter
    }

}