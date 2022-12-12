package com.example.ajoyibrisep.presentation.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ajoyibrisep.data.db.entity.IngredientsModel
import com.example.ajoyibrisep.data.db.entity.PreparationModel
import com.example.ajoyibrisep.presentation.ui.pager.PreparationFragment

class PreparationPager(
    mFragmentActivity: FragmentActivity,
    ingredients: IngredientsModel, actions: PreparationModel
) : FragmentStateAdapter(mFragmentActivity) {
    private val ingList = ArrayList<String>()
    private val prepList = ArrayList<String>()

    init {
        if (ingredients.product1.isNotEmpty()) ingList.add(ingredients.product1)
        if (!ingredients.product2.isNullOrEmpty()) ingList.add(ingredients.product2)
        if (!ingredients.product3.isNullOrEmpty()) ingList.add(ingredients.product3)
        if (!ingredients.product4.isNullOrEmpty()) ingList.add(ingredients.product4)
        if (!ingredients.product5.isNullOrEmpty()) ingList.add(ingredients.product5)
        if (!ingredients.product6.isNullOrEmpty()) ingList.add(ingredients.product6)
        if (!ingredients.product7.isNullOrEmpty()) ingList.add(ingredients.product7)
        if (!ingredients.product8.isNullOrEmpty()) ingList.add(ingredients.product8)
        if (!ingredients.product9.isNullOrEmpty()) ingList.add(ingredients.product9)
        if (!ingredients.product10.isNullOrEmpty()) ingList.add(ingredients.product10)
        if (!ingredients.product11.isNullOrEmpty()) ingList.add(ingredients.product11)
        if (!ingredients.product12.isNullOrEmpty()) ingList.add(ingredients.product12)
        if (!ingredients.product13.isNullOrEmpty()) ingList.add(ingredients.product13)
        if (!ingredients.product14.isNullOrEmpty()) ingList.add(ingredients.product14)
        if (!ingredients.product15.isNullOrEmpty()) ingList.add(ingredients.product15)

        if (!actions.action1.isNullOrEmpty()) prepList.add(actions.action1)
        if (!actions.action2.isNullOrEmpty()) prepList.add(actions.action2)
        if (!actions.action3.isNullOrEmpty()) prepList.add(actions.action3)
        if (!actions.action4.isNullOrEmpty()) prepList.add(actions.action4)
        if (!actions.action5.isNullOrEmpty()) prepList.add(actions.action5)
        if (!actions.action6.isNullOrEmpty()) prepList.add(actions.action6)
        if (!actions.action7.isNullOrEmpty()) prepList.add(actions.action7)
        if (!actions.action8.isNullOrEmpty()) prepList.add(actions.action8)
        if (!actions.action9.isNullOrEmpty()) prepList.add(actions.action9)
        if (!actions.action10.isNullOrEmpty()) prepList.add(actions.action10)
        if (!actions.action11.isNullOrEmpty()) prepList.add(actions.action11)
        if (!actions.action12.isNullOrEmpty()) prepList.add(actions.action12)
        if (!actions.action13.isNullOrEmpty()) prepList.add(actions.action13)
        if (!actions.action14.isNullOrEmpty()) prepList.add(actions.action14)
        if (!actions.action15.isNullOrEmpty()) prepList.add(actions.action15)

    }

    override fun getItemCount() = 2


    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            PreparationFragment(ingList, position)
        } else {
            PreparationFragment(prepList, position)
        }
    }

}