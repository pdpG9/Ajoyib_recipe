package com.example.ajoyibrisep.presentation.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ajoyibrisep.databinding.ItemImagePagerBinding
import com.example.ajoyibrisep.utils.myLog

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
        "ImagePagerFragment:$data".myLog()
        Glide.with(view).load(data).into(binding?.ivPagerItem!!)
    }
}