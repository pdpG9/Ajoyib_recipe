package com.example.ajoyibrisep.presentation.adapter.recipe.view_holder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.databinding.ItemMainCategoryBinding
import com.example.ajoyibrisep.presentation.adapter.CategoryAdapter

class CategoryTypeViewHolder(
    binding: ItemMainCategoryBinding,
    private val listener: (Int) -> Unit
) :
    RecipeViewHolder(binding.root) {
    private val rvCategory: RecyclerView = binding.rvCategory
    private var categoryList:List<CategoryModel>? = null
    private lateinit var adapter: CategoryAdapter

    fun submitList(list:List<CategoryModel>){ categoryList = list;bind() }

    init { rvCategory.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false) }

    override fun bind() {
        categoryList?.let {
        adapter = CategoryAdapter(it, listener)
        rvCategory.adapter = adapter
        } }
}