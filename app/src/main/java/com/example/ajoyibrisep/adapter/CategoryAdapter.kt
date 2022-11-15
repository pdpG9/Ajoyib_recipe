package com.example.ajoyibrisep.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.db.entity.CategoryModel

class CategoryAdapter(val list:List<CategoryModel>,private val listener:(Int)->Unit) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: AppCompatTextView = itemView.findViewById(R.id.tv_category_item)
        init {
            itemView.setOnClickListener {
                listener.invoke(adapterPosition)
            }
        }
        fun bind(data: CategoryModel) {
            title.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}