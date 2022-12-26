package com.example.ajoyibrisep.presentation.adapter

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.databinding.ItemCategoryBinding
import com.example.ajoyibrisep.utils.myInflate
import com.google.android.material.card.MaterialCardView

class CategoryAdapter : ListAdapter<CategoryModel, CategoryAdapter.ViewHolder>(MyDiffUtil) {
    private var lastSelectedCategory = 0
    private var listener: ((Int) -> Unit)? = null

    fun setCategoryClickListener(block: (Int) -> Unit) {
        listener = block
    }

    object MyDiffUtil : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val title: AppCompatTextView = binding.tvCategoryItem
        private val cardView: MaterialCardView = binding.categoryCard

        init {
            itemView.setOnClickListener {
                val t = lastSelectedCategory
                lastSelectedCategory = bindingAdapterPosition
                listener?.invoke(currentList[bindingAdapterPosition].id)
                notifyItemChanged(t)
                notifyItemChanged(lastSelectedCategory)
            }
        }

        fun bind(position: Int) {
            val data = currentList[position]
            if (bindingAdapterPosition == lastSelectedCategory)
                cardView.setCardBackgroundColor(itemView.context.getColor(R.color.selected_category))
            else {
                if (itemView.context.resources.configuration.uiMode == 33) {
                    cardView.setCardBackgroundColor(itemView.context.getColor(R.color.un_selected_category_night))
                } else {
                    cardView.setCardBackgroundColor(itemView.context.getColor(R.color.un_selected_category))
                }
            }
            title.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.bind(parent.myInflate(R.layout.item_category)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }
}