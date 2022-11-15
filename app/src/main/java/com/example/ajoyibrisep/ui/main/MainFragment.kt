package com.example.ajoyibrisep.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.adapter.recipe.RecipeAdapter
import com.example.ajoyibrisep.databinding.FragmentMainBinding
import com.example.ajoyibrisep.db.entity.CategoryModel
import com.example.ajoyibrisep.db.entity.MealModel
import com.example.ajoyibrisep.db.pref.MealPref
import com.example.ajoyibrisep.model.EventBusModel
import com.example.ajoyibrisep.model.RecipeData
import com.example.ajoyibrisep.utils.CATEGORY_DATA
import com.example.ajoyibrisep.utils.MEAl_DATA
import com.example.ajoyibrisep.utils.myLogD
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class MainFragment : Fragment() {
    private var categoryList: List<CategoryModel> = emptyList()
    private var mealItemsList: List<MealModel> = emptyList()
    private var recommendedMealModel: MealModel? = null
    private var recipeDataList = ArrayList<RecipeData>()
    private lateinit var adapter: RecipeAdapter
    private lateinit var rvMain: RecyclerView
    private var indexLoadData = 0
    private var vm: MainViewModel? = null
    private var binding: FragmentMainBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = Color.TRANSPARENT
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vm = MainViewModel()
        initRecycler()
        lifecycle.coroutineScope.launch {
            isShowProgress(true)
            vm?.loadData()
        }
        vm?.apply {
            recommendedMeal.observe(viewLifecycleOwner) {
                indexLoadData++
                if (recommendedMealModel == null) {
                    recommendedMealModel = it
                }
                updateList(RecipeData.MainRecommendedData(recommendedMealModel!!))
                indexLoadData++
                updateList(RecipeData.MainRecipeItemData(mealItemsList))
                isShowProgress(false)
            }
            categoryModel.observe(viewLifecycleOwner) {
                indexLoadData++
                if (categoryList.isEmpty()) {
                    categoryList = it
                }
                updateList(RecipeData.MainCategoryData(categoryList))
            }
            mealsModel.observe(viewLifecycleOwner) {
                if (mealItemsList.isEmpty()) {
                    mealItemsList = it
                }
                lifecycle.coroutineScope.launch {
                    loadRecommended()
                }
            }
        }

    }

    private fun isShowProgress(boolean: Boolean) {
        binding?.itemPlaceHolder?.tvPlaceHolder?.visibility = View.INVISIBLE
        if (boolean) {
            binding?.itemPlaceHolder?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.itemPlaceHolder?.progressBar?.visibility = View.GONE
        }
    }

    private fun updateList(data: RecipeData) {
        if (indexLoadData <= 3) {
            recipeDataList.add(data)
            adapter.notifyItemInserted(indexLoadData)
        }
    }

    private fun initRecycler() {
        rvMain = binding?.rvMain!!
        adapter = RecipeAdapter()
        rvMain.layoutManager = LinearLayoutManager(requireContext())
        rvMain.adapter = adapter
        adapter.categoryClickListener {
            val bundle = bundleOf(
                CATEGORY_DATA to categoryList[it]
            )
            findNavController().navigate(R.id.action_mainFragment_to_categoryFragment, bundle)
            "click category-$it".myLogD()
        }
        adapter.mealsItemClickListener {
            val bundle = bundleOf(
                MEAl_DATA to mealItemsList[it]
            )
            findNavController().navigate(R.id.action_mainFragment_to_mealFragment, bundle)
            "click meals-$it".myLogD()
        }
        adapter.mealsItemLikeClickListener {
            val b = MealPref().isHaveMeal(it)
            if (b) MealPref().removeMeal(it)
            else MealPref().addMeal(it)
            //
            EventBus.getDefault().post(EventBusModel(it))
        }
        adapter.recommendedClickListener {
            val bundle = bundleOf(
                MEAl_DATA to recommendedMealModel
            )
            findNavController().navigate(R.id.action_mainFragment_to_mealFragment, bundle)
            "click recommended".myLogD()

        }
        adapter.setActionBarClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_infoFragment)
            "logo click".myLogD()
        }
        if (indexLoadData == 0) {
            updateList(RecipeData.MainActionBarData(0))
        }
        adapter.submitList(recipeDataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}