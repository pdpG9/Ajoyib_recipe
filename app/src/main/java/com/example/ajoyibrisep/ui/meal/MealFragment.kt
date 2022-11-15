package com.example.ajoyibrisep.ui.meal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.adapter.pager.ImagePager
import com.example.ajoyibrisep.adapter.pager.PreparationPager
import com.example.ajoyibrisep.databinding.FragmentMealBinding
import com.example.ajoyibrisep.db.entity.IngredientsModel
import com.example.ajoyibrisep.db.entity.MealModel
import com.example.ajoyibrisep.db.entity.PreparationModel
import com.example.ajoyibrisep.db.pref.MealPref
import com.example.ajoyibrisep.utils.MEAl_DATA
import kotlinx.coroutines.launch

class MealFragment : Fragment() {
    private var binding: FragmentMealBinding? = null
    private lateinit var vm: MealViewModel
    private lateinit var currentMeal: MealModel
    private lateinit var imagePager: ViewPager2
    private lateinit var ingPager: ViewPager2
    private var isIngClick = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPlaceHolder(true)
        loadView()
        attachClicks()
        lifecycle.coroutineScope.launch {
            loadData()
        }
        vm.apply {
            imagesList.observe(viewLifecycleOwner) {
                val list = ArrayList<String>()
                if (it.image1 != null) list.add(it.image1)
                if (it.image2 != null) list.add(it.image2)
                if (it.image3 != null) list.add(it.image3)
                if (it.image4 != null) list.add(it.image4)
                if (it.image5 != null) list.add(it.image5)
                imagePager.adapter = ImagePager(requireActivity(), list)
                binding?.imageDots?.attachTo(imagePager)
            }
            pagerData.observe(viewLifecycleOwner) {
                ingPager.adapter = PreparationPager(
                    requireActivity(),
                    it as Pair<IngredientsModel, PreparationModel>
                )
                showPlaceHolder(false)
            }


        }

    }

    private fun loadView() {
        imagePager = binding?.imagePager!!
        ingPager = binding?.pagerIngredients!!
        ingPager.isUserInputEnabled = false
    }

    private fun showPlaceHolder(boolean: Boolean) {
        if (boolean) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }

    private fun attachClicks() {
        binding?.apply {
            btBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btLike.setOnClickListener {
                showPlaceHolder(true)
                val b = MealPref().isHaveMeal(currentMeal.id)
                if (b) {
                    MealPref().removeMeal(currentMeal.id)
                } else {
                    MealPref().addMeal(currentMeal.id)
                }
                updateLike()
                showPlaceHolder(false)
            }
            btIngredients.setOnClickListener {
                if (!isIngClick) {
                    ingPager.currentItem = 0
                    isIngClick = true
                    btIngredients.setTextColor(requireContext().getColor(R.color.white))
                    btIngredients.setBackgroundResource(R.drawable.bg_clickable_text)
                    btDetails.setTextColor(requireContext().getColor(R.color.selected_category))
                    btDetails.setBackgroundResource(R.drawable.bg_un_clickable_text)
                }
            }
            btDetails.setOnClickListener {
                if (isIngClick) {
                    ingPager.currentItem = 1
                    isIngClick = false
                    btDetails.setTextColor(requireContext().getColor(R.color.white))
                    btDetails.setBackgroundResource(R.drawable.bg_clickable_text)
                    btIngredients.setTextColor(requireContext().getColor(R.color.selected_category))
                    btIngredients.setBackgroundResource(R.drawable.bg_un_clickable_text)
                }
            }
        }
    }
    private suspend fun loadData() {
        currentMeal = arguments?.getSerializable(MEAl_DATA) as MealModel
        vm = MealViewModel()
        vm.loadData(currentMeal.id)
        binding!!.titleMeal.text = currentMeal.title
        updateLike()
        if (currentMeal.video_uri != null) {
            binding!!.btYouTube.visibility = View.VISIBLE
            binding!!.btYouTube.setOnClickListener {
                val intent = Intent("android.intent.action.VIEW", Uri.parse(currentMeal.video_uri))
                startActivity(intent)
            }
        }else{
            binding!!.btYouTube.visibility = View.INVISIBLE
        }
        if (currentMeal.web_url!=null){
            binding!!.titleMeal.setOnClickListener {
                val intent = Intent("android.intent.action.VIEW", Uri.parse(currentMeal.web_url))
                startActivity(intent)
            }
        }
    }

    private fun updateLike() {
        val b = MealPref().isHaveMeal(currentMeal.id)
        if (b) {
            binding!!.ivLike.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding!!.ivLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}