package com.example.ajoyibrisep.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ajoyibrisep.db.entity.IngredientsModel
import com.example.ajoyibrisep.db.entity.PreparationModel
import com.example.ajoyibrisep.ui.pager.PreparationFragment

class PreparationPager(
    mFragmentActivity: FragmentActivity,
    data: Pair<IngredientsModel, PreparationModel>
) : FragmentStateAdapter(mFragmentActivity) {
    private val ingList = ArrayList<String>()
    private val prepList = ArrayList<String>()

    init {
        val i = data.first
        if (!i.product1.isEmpty()) ingList.add(i.product1)
        if (!i.product2.isNullOrEmpty()) ingList.add(i.product2)
        if (!i.product3.isNullOrEmpty()) ingList.add(i.product3)
        if (!i.product4.isNullOrEmpty()) ingList.add(i.product4)
        if (!i.product5.isNullOrEmpty()) ingList.add(i.product5)
        if (!i.product6.isNullOrEmpty()) ingList.add(i.product6)
        if (!i.product7.isNullOrEmpty()) ingList.add(i.product7)
        if (!i.product8.isNullOrEmpty()) ingList.add(i.product8)
        if (!i.product9.isNullOrEmpty()) ingList.add(i.product9)
        if (!i.product10.isNullOrEmpty()) ingList.add(i.product10)
        if (!i.product11.isNullOrEmpty()) ingList.add(i.product11)
        if (!i.product12.isNullOrEmpty()) ingList.add(i.product12)
        if (!i.product13.isNullOrEmpty()) ingList.add(i.product13)
        if (!i.product14.isNullOrEmpty()) ingList.add(i.product14)
        if (!i.product15.isNullOrEmpty()) ingList.add(i.product15)
        val p = data.second
        if (!p.action1.isNullOrEmpty()) prepList.add(p.action1)
        if (!p.action2.isNullOrEmpty()) prepList.add(p.action2)
        if (!p.action3.isNullOrEmpty()) prepList.add(p.action3)
        if (!p.action4.isNullOrEmpty()) prepList.add(p.action4)
        if (!p.action5.isNullOrEmpty()) prepList.add(p.action5)
        if (!p.action6.isNullOrEmpty()) prepList.add(p.action6)
        if (!p.action7.isNullOrEmpty()) prepList.add(p.action7)
        if (!p.action8.isNullOrEmpty()) prepList.add(p.action8)
        if (!p.action9.isNullOrEmpty()) prepList.add(p.action9)
        if (!p.action10.isNullOrEmpty()) prepList.add(p.action10)
        if (!p.action11.isNullOrEmpty()) prepList.add(p.action11)
        if (!p.action12.isNullOrEmpty()) prepList.add(p.action12)
        if (!p.action13.isNullOrEmpty()) prepList.add(p.action13)
        if (!p.action14.isNullOrEmpty()) prepList.add(p.action14)
        if (!p.action15.isNullOrEmpty()) prepList.add(p.action15)

    }

    override fun getItemCount() = 2


    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            PreparationFragment(ingList,position)
        } else {
            PreparationFragment(prepList,position)
        }
    }

}