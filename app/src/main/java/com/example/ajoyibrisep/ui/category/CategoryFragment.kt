package com.example.ajoyibrisep.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajoyibrisep.R
import com.example.ajoyibrisep.adapter.MealsAdapter
import com.example.ajoyibrisep.databinding.FragmentCategoryBinding
import com.example.ajoyibrisep.db.entity.CategoryModel
import com.example.ajoyibrisep.db.entity.MealModel
import com.example.ajoyibrisep.db.pref.MealPref
import com.example.ajoyibrisep.utils.CATEGORY_DATA
import com.example.ajoyibrisep.utils.MEAl_DATA
import kotlinx.coroutines.launch



class CategoryFragment : Fragment() {
    private var binding: FragmentCategoryBinding? = null
    private lateinit var adapter: MealsAdapter
    private lateinit var vm: CategoryViewModel
    private lateinit var rvMeals: RecyclerView
    private var currentList = ArrayList<MealModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = CategoryViewModel()
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        binding?.btBackCategory?.setOnClickListener {
            findNavController().popBackStack()
        }
        lifecycle.coroutineScope.launch {
            binding?.itemPlaceHolder?.progressBar?.visibility = View.VISIBLE
            loadData()
        }
        vm.apply {
            isShowPlaceHolder.observe(viewLifecycleOwner) {
                if (it)
                    binding?.itemPlaceHolder?.tvPlaceHolder?.visibility = View.VISIBLE
                else
                    binding?.itemPlaceHolder?.tvPlaceHolder?.visibility = View.GONE
            }
            productList.observe(viewLifecycleOwner) {
                currentList.clear()
                currentList.addAll(it)
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
                binding?.itemPlaceHolder?.progressBar?.visibility = View.GONE
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initList() {
        rvMeals = binding?.rvCategoryFragment!!
        rvMeals.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MealsAdapter()
        adapter.setListener {
            val bundle = bundleOf(
                MEAl_DATA to currentList[it]
            )
            findNavController().navigate(R.id.action_categoryFragment_to_mealFragment, bundle)
        }
        adapter.setLikeListener {
            val b = MealPref().isHaveMeal(it)
            if (b) MealPref().removeMeal(it)
            else MealPref().addMeal(it)
            adapter.notifyItemChanged(it)
        }
        rvMeals.adapter = adapter

    }

    private suspend fun loadData() {
        val category = arguments?.getSerializable(CATEGORY_DATA) as CategoryModel
        binding?.titleCategoryList?.text = category.name
        vm.loadList(category.id)
    }
}