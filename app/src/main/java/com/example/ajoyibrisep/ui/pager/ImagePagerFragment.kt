package com.example.ajoyibrisep.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ajoyibrisep.databinding.ItemImagePagerBinding

class ImagePagerFragment(private val data: String) : Fragment() {
    private var binding: ItemImagePagerBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemImagePagerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view).load(data).into(binding?.ivPagerItem!!)
    }
}