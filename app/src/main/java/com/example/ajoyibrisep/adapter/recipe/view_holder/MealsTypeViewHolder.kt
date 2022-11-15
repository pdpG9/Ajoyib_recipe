package com.example.ajoyibrisep.adapter.recipe.view_holder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.adapter.MealsAdapter
import com.example.ajoyibrisep.model.EventBusModel
import com.example.ajoyibrisep.model.RecipeData
import com.example.ajoyibrisep.utils.myLogD
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MealsTypeViewHolder(
    view: View,
    private val listener: (Int) -> Unit,
    private val likeListener: (Int) -> Unit
) :
    RecipeViewHolder(view) {
    private val rvMealsList: RecyclerView = view.findViewById(R.id.rv_recipe)
    private val adapter = MealsAdapter()

    init {
        rvMealsList.layoutManager = GridLayoutManager(view.context, 2)
        rvMealsList.adapter = adapter
        EventBus.getDefault().register(this)
    }

    override fun bind(data: RecipeData) {
        val list = (data as RecipeData.MainRecipeItemData).recipeList
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
        adapter.setListener {
            listener.invoke(it)
        }
        adapter.setLikeListener {
            likeListener.invoke(it)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventBusModel?) {
        "eventBus ${event?.position}".myLogD()
        adapter.notifyItemChanged(event?.position ?: 0)
    }

}