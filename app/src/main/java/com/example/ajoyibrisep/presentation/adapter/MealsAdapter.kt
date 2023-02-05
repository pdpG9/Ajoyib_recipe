package com.example.ajoyibrisep.presentation.adapter

import android.util.Log
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
import kotlinx.coroutines.flow.flow


class MealsAdapter : ListAdapter<MealModel, MealsAdapter.MealViewHolder>(MyDiffUtil) {
    private lateinit var itemClickListener: ((Int) -> Unit)
    private var hour = 0
    private var minute = 0
    fun setListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    object MyDiffUtil : DiffUtil.ItemCallback<MealModel>() {
        override fun areItemsTheSame(oldItem: MealModel, newItem: MealModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MealModel, newItem: MealModel): Boolean {
            return oldItem == newItem
        }
    }


    inner class MealViewHolder(binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { itemClickListener.invoke(currentList[bindingAdapterPosition].id) }
        }

        private val imageView: AppCompatImageView = binding.ivItemRecipe
        private val title: AppCompatTextView = binding.tvItemTitle
        private val timeHour: AppCompatTextView = binding.tvTimeHour
        private val textHour: AppCompatTextView = binding.hourText
        private val minuteText: AppCompatTextView = binding.minuteText
        private val timeMinute: AppCompatTextView = binding.tvTimeMinute
        private val ratingText: AppCompatTextView = binding.tvRatingItem
        fun bind(position: Int) {
            val data = currentList[position]
           calcTime(data.time)
            Glide.with(itemView).load(data.image)
                .placeholder(R.drawable.image_menu)
                .into(imageView)
            title.text = data.title
            ratingText.text = data.rating
        }

        private fun calcTime(time: Int) {
            if (time < 60) {
                timeHour.text = "0"
                timeMinute.text = time.toString()
                checkHour(0,time)
                return
            }else{
                timeHour.text = (time/60).toString()
                timeMinute.text = "${time%60}"
                checkHour(time/60,time%60)
            }
        }

        private fun checkHour(hour: Int, minute: Int){
            if (hour == 0) {
                timeHour.visibility = View.GONE
                textHour.visibility = View.GONE
            } else {
                timeHour.visibility = View.VISIBLE
                textHour.visibility = View.VISIBLE
            }
            if (minute == 0) {
                minuteText.visibility = View.GONE
                timeMinute.visibility = View.GONE
            } else {
                minuteText.visibility = View.VISIBLE
                timeMinute.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MealViewHolder(ItemRecipeBinding.bind(parent.myInflate(R.layout.item_recipe)))

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) = holder.bind(position)
}