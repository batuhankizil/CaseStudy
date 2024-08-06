package com.example.study.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.ViewModel.MainViewModel
import com.example.study.adapter.CategoryAdapter
import com.example.study.adapter.FoodsAdapter
import com.example.study.databinding.FragmentHomePageBinding
import com.example.study.itemDecoration

class HomePageFragment : Fragment() {

    val viewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        //viewModel.fetchData()

        binding.recyclerCategory.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        viewModel.getCategoryModelLiveData().observe(viewLifecycleOwner) {
            val adapter =
                CategoryAdapter(it, onItemClick = { id -> viewModel.updateCategoryList(id) })
            binding.recyclerCategory.adapter = adapter
        }

        binding.recyclerFoods.layoutManager = GridLayoutManager(context, 2)
        val adapterFoods = FoodsAdapter(viewModel.getModels())
        binding.recyclerFoods.adapter = adapterFoods

        context?.let { itemDecoration(it, spanCount = 2, spacingDp = 17) }
            ?.let { binding.recyclerFoods.addItemDecoration(it) }

        adapterFoods.onItemClick = {
            /*val fragment = FoodDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("food", it)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()*/

            val bundle = Bundle()
            bundle.putParcelable("food", it)
            findNavController().navigate(R.id.foodDetailFragment, bundle)
        }




        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_foodDetailFragment)
        }

        return binding.root
    }

}