package com.example.ajoyibrisep.presentation.ui.screen

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.data.model.IngredientsData
import com.example.ajoyibrisep.databinding.ScreenMealBinding
import com.example.ajoyibrisep.presentation.adapter.pager.ImagePager
import com.example.ajoyibrisep.presentation.adapter.pager.PreparationPager
import com.example.ajoyibrisep.presentation.viewmodel.MealViewModel
import com.example.ajoyibrisep.presentation.viewmodel.imp.MealViewModelImp
import com.example.ajoyibrisep.utils.myLog
import com.example.ajoyibrisep.utils.showStatusBar
import com.example.ajoyibrisep.utils.uploadStatusBarColor

class MealScreen : Fragment(R.layout.screen_meal) {
    private val binding: ScreenMealBinding by viewBinding(ScreenMealBinding::bind)
    private val vm: MealViewModel by viewModels<MealViewModelImp>()
    private lateinit var imageAdapter: ImagePager
    private lateinit var preparationAdapter: PreparationPager
    private var mode: Int = Configuration.UI_MODE_NIGHT_NO
    private var lastSelectedIng = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().showStatusBar()
        requireActivity().uploadStatusBarColor(requireContext())
        mode = requireContext().resources.configuration.uiMode
        vm.apply {
            moveToWebLiveData.observe(this@MealScreen, moveToWebObserver)
            moveToYouTubeLiveData.observe(this@MealScreen, moveToYouTubeObserver)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        attachActions()
        setModeToIngredientsButton(lastSelectedIng)
        "uiMode:$mode".myLog()
        vm.apply {
            loadCurrentMeal(arguments?.getInt("MEAL_ID", -1) ?: -1)
            moveToBackLiveData.observe(viewLifecycleOwner, moveToBackObserver)
            titleLiveData.observe(viewLifecycleOwner, titleObserver)
            actionsLiveData.observe(viewLifecycleOwner, actionsObserver)
            imagesLiveData.observe(viewLifecycleOwner, imagesListObserver)
            showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
            clickIngredientsLiveData.observe(viewLifecycleOwner, clickIngredientObserver)
            stateYouTubeButtonLiveData.observe(viewLifecycleOwner, youTubeButtonStateObserver)
        }
    }

    private fun attachActions() {
        binding.apply {
            btBack.setOnClickListener { vm.clickBack() }
            btIngredients.setOnClickListener { vm.clickIngredients() }
            btDetails.setOnClickListener { vm.clickActions() }
            btLink.setOnClickListener { vm.clickLink() }
            btYouTube.setOnClickListener { vm.clickYouTube() }
        }
    }

    private val showProgressObserver =
        Observer<Boolean> { if (it) binding.progressBar.show() else binding.progressBar.hide() }
    private val imagesListObserver =
        Observer<List<String>> { imageAdapter = ImagePager(requireActivity(), it);binding.imagePager.adapter = imageAdapter }
    private val titleObserver = Observer<String> { binding.titleMeal.text = it }
    private val actionsObserver = Observer<IngredientsData> {
        preparationAdapter = PreparationPager(requireActivity(), it.ingredients, it.preparation)
        binding.pagerIngredients.adapter = preparationAdapter
    }
    private val moveToYouTubeObserver = Observer<Intent> { startActivity(it) }
    private val moveToWebObserver = Observer<Intent> { startActivity(it) }
    private val moveToBackObserver = Observer<Unit> { findNavController().popBackStack() }
    private val clickIngredientObserver = Observer<Boolean> { setChangesPreparation(it) }
    private val youTubeButtonStateObserver =
        Observer<Boolean> { if (it) binding.btYouTube.visibility = View.VISIBLE else binding.btYouTube.visibility = View.INVISIBLE }

    private fun setChangesPreparation(b: Boolean) {
        binding.apply {
            setModeToIngredientsButton(b)
            lastSelectedIng = b
            if (b) {
                pagerIngredients.currentItem = 0
                btIngredients.setTextColor(requireContext().getColor(R.color.white))
                btIngredients.setBackgroundResource(R.drawable.bg_clickable_text)
                btDetails.setTextColor(requireContext().getColor(R.color.selected_category))
            } else {
                pagerIngredients.currentItem = 1
                btDetails.setTextColor(requireContext().getColor(R.color.white))
                btDetails.setBackgroundResource(R.drawable.bg_clickable_text)
                btIngredients.setTextColor(requireContext().getColor(R.color.selected_category))
            }
        }
    }

    private fun setModeToIngredientsButton(b: Boolean) {
        if (b) {
            if (mode == 33) {
                binding.btDetails.setBackgroundResource(R.drawable.bg_ingredients_night)
            } else {
                binding.btDetails.setBackgroundResource(R.drawable.bg_ingredients_light)
            }
        } else {
            if (mode == 33) {
                binding.btIngredients.setBackgroundResource(R.drawable.bg_ingredients_night)
            } else {
                binding.btIngredients.setBackgroundResource(R.drawable.bg_ingredients_light)
            }
        }

    }
}