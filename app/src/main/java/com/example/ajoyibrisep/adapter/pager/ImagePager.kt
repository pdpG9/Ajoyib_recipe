package com.example.ajoyibrisep.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ajoyibrisep.ui.pager.ImagePagerFragment

class ImagePager(mFragmentActivity: FragmentActivity, val list:List<String>) :
    FragmentStateAdapter(mFragmentActivity) {

    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return ImagePagerFragment(list[position])
    }

}