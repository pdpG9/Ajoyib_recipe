package com.example.ajoyibrisep.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.databinding.ScreenSplashBinding
import com.example.ajoyibrisep.presentation.viewmodel.SplashViewModel
import com.example.ajoyibrisep.presentation.viewmodel.imp.SplashViewModelImp
import com.example.ajoyibrisep.utils.uploadStatusBarColor


@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val vm: SplashViewModel by viewModels<SplashViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().uploadStatusBarColor(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btGetStarted.setOnClickListener { vm.clickAction() }
        vm.moveToMainScreen.observe(viewLifecycleOwner, moveToMainObserver)
    }

    private val moveToMainObserver = Observer<Unit> { findNavController().navigate(R.id.action_menuScreen_to_mainScreen) }

}