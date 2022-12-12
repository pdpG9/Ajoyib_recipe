package com.example.ajoyibrisep.presentation.ui.screen

import android.content.res.Configuration
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.databinding.ScreenInfoBinding
import com.example.ajoyibrisep.presentation.viewmodel.InfoViewModel
import com.example.ajoyibrisep.presentation.viewmodel.imp.InfoViewModelImp
import com.example.ajoyibrisep.utils.showStatusBar
import com.example.ajoyibrisep.utils.uploadStatusBarColor

class InfoScreen : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)
    private val vm: InfoViewModel by viewModels<InfoViewModelImp>()
    private var mode: Int = Configuration.UI_MODE_NIGHT_NO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().showStatusBar()
        requireActivity().uploadStatusBarColor(requireContext())
        mode = requireContext().resources.configuration.uiMode
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvInfo.text = Html.fromHtml(getString(R.string.info_description))
        binding.btBack.setOnClickListener { vm.clickBack() }
        vm.moveToBackLiveData.observe(viewLifecycleOwner, moveToBackObserver)
    }

    private val moveToBackObserver = Observer<Unit> { findNavController().popBackStack() }
}