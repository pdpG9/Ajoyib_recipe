package com.example.ajoyibrisep.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.db.entity.MealModel


class MealsAdapter :
    RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {
    private var list: List<MealModel> = emptyList()
    private lateinit var itemClickListener: ((Int) -> Unit)
    private lateinit var itemLikeClickListener: ((Int) -> Unit)

    fun submitList(l: List<MealModel>) {
        list = l
    }

    fun setListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    fun setLikeListener(listener: (Int) -> Unit) {
        itemLikeClickListener = listener
    }

    inner class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                itemClickListener.invoke(adapterPosition)
            }
        }

        private val imageView: AppCompatImageView = itemView.findViewById(R.id.iv_itemRecipe)
        private val title: AppCompatTextView = itemView.findViewById(R.id.tv_itemTitle)
        private val timeHour: AppCompatTextView = itemView.findViewById(R.id.tv_timePrepareHour)
        private val textHour: AppCompatTextView = itemView.findViewById(R.id.hourText)
        private val minuteText: AppCompatTextView = itemView.findViewById(R.id.minuteText)
        private val timeMinute: AppCompatTextView = itemView.findViewById(R.id.tv_timePrepareMinute)
        private val ratingText: AppCompatTextView = itemView.findViewById(R.id.tv_ratingItem)
        fun bind(data: MealModel) {
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
            Log.d("TAG", "checkHour: $hour : $minute")
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        )
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount() = list.size


}