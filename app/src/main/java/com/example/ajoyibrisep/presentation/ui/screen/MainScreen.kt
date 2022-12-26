package com.example.ajoyibrisep.presentation.ui.screen

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.data.db.entity.CategoryModel
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.databinding.ScreenMainBinding
import com.example.ajoyibrisep.presentation.adapter.HomeCategoryController
import com.example.ajoyibrisep.presentation.adapter.HomeRecipeListController
import com.example.ajoyibrisep.presentation.adapter.HomeRecommendedController
import com.example.ajoyibrisep.presentation.viewmodel.MainViewModel
import com.example.ajoyibrisep.presentation.viewmodel.imp.MainViewModelImp
import com.example.ajoyibrisep.utils.myLog
import com.example.ajoyibrisep.utils.showProgress
import com.example.ajoyibrisep.utils.uploadStatusBarColor
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val vm: MainViewModel by viewModels<MainViewModelImp>()
    private var mode: Int = Configuration.UI_MODE_NIGHT_NO
    private val categoryController by lazy { HomeCategoryController() }
    private val recommendedController by lazy { HomeRecommendedController() }
    private val mealsController by lazy { HomeRecipeListController() }
    private val adapter = EasyAdapter().apply { isFirstInvisibleItemEnabled = true }
    private val itemList = ItemList()
    private val defItemList = ItemList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().uploadStatusBarColor(requireContext())
        mode = requireContext().resources.configuration.uiMode
        vm.moveToInfoScreenLiveData.observe(this, moveToInfoScreenObserver)
        vm.showProgressLiveData.observe(this, showProgressObserver)
        vm.moveToMealScreenLiveData.observe(this, moveToMealScreenObserver)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
        vm.apply {
            loadData()
            recommendedLiveData.observe(viewLifecycleOwner, recommendedObserver)
            categoriesLiveData.observe(viewLifecycleOwner, categoriesObserver)
            mealsLiveData.observe(viewLifecycleOwner, mealsObserver)
        }
    }

    private fun initRecycler() {
        categoryController.setCategoryClickListener { vm.clickItemCategory(it) }
        mealsController.setListener { vm.clickItemMeal(it) }
        recommendedController.setItemClickListener { vm.clickItemRecommended() }
        binding.rvMain.adapter = adapter
        binding.btLogo.setOnClickListener { vm.clickLogoButton() }
    }

    private val moveToMealScreenObserver =
        Observer<Int> { findNavController().navigate(R.id.action_mainScreen_to_mealScreen, bundleOf("MEAL_ID" to it)) }
    private val moveToInfoScreenObserver = Observer<Unit> { findNavController().navigate(R.id.action_mainScreen_to_infoScreen) }
    private val showProgressObserver = Observer<Boolean> { binding.itemPlaceHolder.progressBar.showProgress(it) }
    private val categoriesObserver =
        Observer<List<CategoryModel>> {
            "categoriesObserver:${itemList.size}".myLog();if (itemList.isEmpty()) itemList.add(
            it,
            categoryController
        );defItemList.add(it, categoryController)
        }
    private val recommendedObserver =
        Observer<MealModel> {
            "recommendedObserver:${itemList.size}".myLog(); if (itemList.size == 1) itemList.add(
            it,
            recommendedController
        );defItemList.add(it, recommendedController)
        }
    private val mealsObserver = Observer<List<MealModel>> { "mealsObserver:${itemList.size}".myLog();setMealsController(it) }

    private fun setMealsController(list: List<MealModel>) {
        if (itemList.size == 2) itemList.add(list, mealsController) else {
            itemList.clear()
            itemList.add(defItemList[0])
            itemList.add(defItemList[1])
            itemList.add(list, mealsController)
        }
        adapter.setItems(itemList)
        "setMealsController:${itemList.size}".myLog()
    }
}