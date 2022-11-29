package com.example.ajoyibrisep.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.databinding.FragmentMenuBinding
import com.example.ajoyibrisep.utils.changeColorByMode


class MenuFragment : Fragment() {
    private lateinit var viewModel: MenuViewModel
    private var binding: FragmentMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        requireActivity().window.statusBarColor = requireActivity().getColor(R.color.white)
        requireActivity().window.changeColorByMode(false,R.color.black)
        binding?.btGetStarted?.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_firstFragment)
        }
    }

    override fun onDestroyView() {
        binding = null
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDestroyView()
    }
}