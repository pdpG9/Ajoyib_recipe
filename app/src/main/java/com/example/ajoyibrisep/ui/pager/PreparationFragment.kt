package com.example.ajoyibrisep.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.adapter.PagerItemAdapter
import com.example.ajoyibrisep.databinding.PageIngredientsBinding

class PreparationFragment(private val data: List<String>, private val type: Int) : Fragment() {
    private var binding: PageIngredientsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PageIngredientsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var text = "${data.size} "
        text += if (type == 0) {
            getString(R.string.mahsulotlar)
        } else {
            getString(R.string.jarayon)
        }

        binding?.tvCountList?.text = text
        binding!!.rvListPager.apply {
            this.layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = PagerItemAdapter(data)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}