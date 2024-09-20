package com.example.study.fragment

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.ViewModel.FoodDetailViewModel
import com.example.study.ViewModel.MainViewModel
import com.example.study.databinding.FragmentFoodDetailBinding
import com.example.study.data.FoodsModelResponse
import com.example.study.domain.FoodsUIModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {

    private val viewModel: FoodDetailViewModel by viewModels()
    private lateinit var binding: FragmentFoodDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)

        val foodJson = arguments?.getString("foodJson")

        viewModel.setFoodJson(foodJson)

        lifecycleScope.launch {
            viewModel.foodDetail.collect { foodsModel ->
                if (foodsModel != null) {
                    binding.foodName.text = foodsModel.foodName
                    binding.foodRank.text = foodsModel.foodRank.toString()
                    foodsModel.foodImage.let { imageUrl ->
                        Glide.with(binding.foodImg.context)
                            .load(imageUrl)
                            .into(binding.foodImg)
                    }
                    binding.foodDetail.text = foodsModel.foodDetail

                    if (foodsModel.discount) {
                        binding.discount.visibility = View.VISIBLE
                        binding.discount.text = foodsModel.discountPrice.toString()
                        binding.foodPrice.text = foodsModel.foodPrice.toString()
                        binding.foodPrice.setTextColor(Color.GRAY)
                        binding.foodPrice.paintFlags =
                            binding.foodPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        binding.foodPrice.text = foodsModel.foodPrice.toString()
                    }
                } else binding.foodDetail.text = R.string.failed_to_load.toString()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}