package com.example.ajoyibrisep.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R

class PagerItemAdapter(private val list: List<String>) :
    RecyclerView.Adapter<PagerItemAdapter.PagerVH>() {
    inner class PagerVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: AppCompatTextView = view.findViewById(R.id.tv_itemIng)
        fun bind(data: String) {
            tvTitle.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        return PagerVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}