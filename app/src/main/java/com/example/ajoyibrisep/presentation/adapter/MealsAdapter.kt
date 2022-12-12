package com.example.ajoyibrisep.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.databinding.ItemRecipeBinding
import com.example.ajoyibrisep.utils.myInflate


class MealsAdapter : ListAdapter<MealModel, MealsAdapter.MealViewHolder>(MyDiffUtil) {
    private lateinit var itemClickListener: ((Int) -> Unit)

    object MyDiffUtil : DiffUtil.ItemCallback<MealModel>() {
        override fun areItemsTheSame(oldItem: MealModel, newItem: MealModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MealModel, newItem: MealModel): Boolean {
            return oldItem == newItem
        }
    }

    fun setListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    inner class MealViewHolder(binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { itemClickListener.invoke(currentList[bindingAdapterPosition].id) }
        }

        private val imageView: AppCompatImageView = itemView.findViewById(R.id.iv_itemRecipe)
        private val title: AppCompatTextView = itemView.findViewById(R.id.tv_itemTitle)
        private val timeHour: AppCompatTextView = itemView.findViewById(R.id.tv_timePrepareHour)
        private val textHour: AppCompatTextView = itemView.findViewById(R.id.hourText)
        private val minuteText: AppCompatTextView = itemView.findViewById(R.id.minuteText)
        private val timeMinute: AppCompatTextView = itemView.findViewById(R.id.tv_timePrepareMinute)
        private val ratingText: AppCompatTextView = itemView.findViewById(R.id.tv_ratingItem)
        fun bind(position: Int) {
            val data = currentList[position]
            val hour: Int = data.time / 60
            val minute: Int = data.time % 60
            timeHour.text = hour.toString()
            timeMinute.text = hour.toString()
            checkHour(hour, minute)
            Glide.with(itemView).load(data.image)
                .placeholder(R.drawable.image_menu)
                .into(imageView)
            title.text = data.title
            ratingText.text = data.rating
        }

        private fun checkHour(hour: Int, minute: Int) {
            if (hour == 0) {
                timeHour.visibility = View.GONE
                textHour.visibility = View.GONE
            } else {
                timeHour.visibility = View.VISIBLE
                textHour.visibility = View.VISIBLE
                timeHour.text = hour.toString()
            }
            if (minute == 0) {
                minuteText.visibility = View.GONE
                timeMinute.visibility = View.GONE
            } else {
                minuteText.visibility = View.VISIBLE
                timeMinute.visibility = View.VISIBLE
                timeMinute.text = minute.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MealViewHolder(ItemRecipeBinding.bind(parent.myInflate(R.layout.item_recipe)))

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) = holder.bind(position)
}