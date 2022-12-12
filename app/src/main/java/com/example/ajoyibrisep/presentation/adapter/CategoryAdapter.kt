package com.example.ajoyibrisep.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.google.android.material.card.MaterialCardView

class CategoryAdapter(val list: List<CategoryModel>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var lastSelectedCategory = 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: AppCompatTextView = itemView.findViewById(R.id.tv_category_item)
        private val cardView: MaterialCardView = itemView.findViewById(R.id.categoryCard)

        init {
            itemView.setOnClickListener {
                val t = lastSelectedCategory
                lastSelectedCategory = bindingAdapterPosition
                listener.invoke(list[bindingAdapterPosition].id)
                notifyItemChanged(t)
                notifyItemChanged(lastSelectedCategory)
            }
        }

        fun bind(position: Int) {
            val data = list[position]
            if (bindingAdapterPosition == lastSelectedCategory)
                cardView.setCardBackgroundColor(itemView.context.getColor(R.color.selected_category))
            else{
                if (itemView.context.resources.configuration.uiMode==33){
                cardView.setCardBackgroundColor(itemView.context.getColor(R.color.un_selected_category_night))
                }else{
                cardView.setCardBackgroundColor(itemView.context.getColor(R.color.un_selected_category))
                }
            }
            title.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = list.size
}