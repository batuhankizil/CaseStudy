package com.example.study.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
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
import com.example.study.sharedPreferences.LoginDataSource
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentHomePageBinding

    @Inject
    lateinit var loginDataSource: LoginDataSource


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomePageBinding.inflate(inflater, container, false)


        lifecycleScope.launch {
            viewModel.username.collect { username ->
                if (username != null) {
                    binding.username.text = "Welcome, $username"
                }
            }
        }

        binding.logout.setOnClickListener {
            loginDataSource.logout()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.homePageFragment, true)
                .build()
            findNavController().navigate(R.id.loginFragment, null, navOptions)
        }

        binding.recyclerCategory.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        lifecycleScope.launch {
            viewModel.getCategoryModelStateFlow().collect {
                    categories ->
                val categoryItems = categories.map { CollectiveModel.Category(it) }
                val adapter = CollectiveAdapter(items = categoryItems,
                    onCategoryClick = { id -> viewModel.updateCategoryList(id)
                    })
                binding.recyclerCategory.adapter = adapter
            }
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
            findNavController().navigate(R.id.action_homePageFragment_to_retrofitFragment)
        }

        return binding.root
    }
}
