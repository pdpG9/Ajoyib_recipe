package com.example.ajoyibrisep.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.databinding.ItemMainCategoryBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class HomeCategoryController : BindableItemController<List<CategoryModel>, HomeCategoryController.Holder>() {
    private var categoryClickListener: ((Int) -> Unit)? = null
    fun setCategoryClickListener(block: (Int) -> Unit) {
        categoryClickListener = block
    }

    inner class Holder(binding: ItemMainCategoryBinding) : BindableViewHolder<List<CategoryModel>>(binding.root) {
        private val adapter = CategoryAdapter()
        private val categoryRv = binding.rvCategory

        init {
            categoryRv.adapter = adapter
            categoryRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter.setCategoryClickListener { categoryClickListener?.invoke(it) }
        }

        override fun bind(data: List<CategoryModel>?) { adapter.submitList(data) }

    }

    override fun createViewHolder(parent: ViewGroup?): Holder =
        Holder(ItemMainCategoryBinding.inflate(LayoutInflater.from(parent?.context), parent, false))


    override fun getItemId(data: List<CategoryModel>?) = ID_TAG

    private companion object {
        const val ID_TAG = "HomeCategoryController"
    }
}