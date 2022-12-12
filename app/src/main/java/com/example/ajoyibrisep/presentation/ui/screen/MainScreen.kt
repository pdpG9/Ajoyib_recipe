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
import com.example.ajoyibrisep.presentation.adapter.recipe.RecipeAdapter
import com.example.ajoyibrisep.presentation.viewmodel.MainViewModel
import com.example.ajoyibrisep.presentation.viewmodel.imp.MainViewModelImp
import com.example.ajoyibrisep.utils.showProgress
import com.example.ajoyibrisep.utils.showStatusBar
import com.example.ajoyibrisep.utils.uploadStatusBarColor

class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val vm: MainViewModel by viewModels<MainViewModelImp>()
    private var mode: Int = Configuration.UI_MODE_NIGHT_NO
    private val adapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().showStatusBar()
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
        adapter.setActionBarClickListener { vm.clickLogoButton() }
        adapter.categoryClickListener { vm.clickItemCategory(it) }
        adapter.mealsItemClickListener { vm.clickItemMeal(it) }
        adapter.recommendedClickListener { vm.clickItemRecommended() }
        binding.rvMain.adapter = adapter
        binding.btLogo.setOnClickListener { vm.clickLogoButton() }
    }

    private val moveToMealScreenObserver =
        Observer<Int> { findNavController().navigate(R.id.action_mainScreen_to_mealScreen, bundleOf("MEAL_ID" to it)) }
    private val moveToInfoScreenObserver = Observer<Unit> { findNavController().navigate(R.id.action_mainScreen_to_infoScreen) }
    private val showProgressObserver = Observer<Boolean> { binding.itemPlaceHolder.progressBar.showProgress(it) }
    private val categoriesObserver = Observer<List<CategoryModel>> { adapter.setCategoryList(it) }
    private val mealsObserver = Observer<List<MealModel>> { adapter.setMealsList(it) }
    private val recommendedObserver = Observer<MealModel> { adapter.setRecommendedMeal(it) }

}