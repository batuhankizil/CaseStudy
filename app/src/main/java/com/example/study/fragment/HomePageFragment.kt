package com.example.study.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.ViewModel.MainViewModel
import com.example.study.databinding.FragmentHomePageBinding
import com.example.study.ItemDecoration
import com.example.study.adapter.CollectiveAdapter
import com.example.study.adapter.CollectiveModel
import com.example.study.data.ProductRepository
import com.example.study.domain.mapper.ProductMapper
import com.example.study.domain.usecase.ProductUseCase
import com.google.gson.Gson
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {

    //val viewModel: MainViewModel by viewModels()

    private lateinit var viewModel: MainViewModel


    private lateinit var binding: FragmentHomePageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        val repository = ProductRepository(requireContext())
        val mapper = ProductMapper()
        val useCase = ProductUseCase(repository, mapper)
        viewModel = MainViewModel(useCase)

        binding.recyclerCategory.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        viewModel.getCategoryModelLiveData().observe(viewLifecycleOwner) { categories ->
            val categoryItems = categories.map { CollectiveModel.Category(it) }

            val adapter = CollectiveAdapter(items = categoryItems,
                onCategoryClick = { id -> viewModel.updateCategoryList(id) })
            binding.recyclerCategory.adapter = adapter
        }

        binding.recyclerFoods.layoutManager = GridLayoutManager(context, 2)

        lifecycleScope.launch {
            viewModel.getFoodsModelStateFlow().collect { foods ->
                val gson = Gson()
                val foodItems = foods.map { CollectiveModel.Food(it) }
                val collectiveAdapter = CollectiveAdapter(items = foodItems,
                    onCategoryClick = { },
                    onFoodClick = {
                        val foodJson = gson.toJson(it)
                        val bundle = Bundle().apply {
                            putString("foodJson", foodJson)
                        }
                        findNavController().navigate(R.id.foodDetailFragment, bundle)
                    })
                binding.recyclerFoods.adapter = collectiveAdapter
                context?.let { ItemDecoration(it, spanCount = 2, spacingDp = 17) }
                    ?.let { binding.recyclerFoods.addItemDecoration(it) }
            }
        }

        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_foodDetailFragment)
        }
        return binding.root
    }

}