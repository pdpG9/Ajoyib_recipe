package com.example.ajoyibrisep.ui.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ajoyibrisep.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private var binding: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.logo.setOnClickListener {
            val intent = Intent(
                "android.intent.action.VIEW",
                Uri.parse("https://www.youtube.com/c/GITADasturchilarAkademiyasi")
            )
            startActivity(intent)
        }
    }
}