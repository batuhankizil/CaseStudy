package com.example.study.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.study.R
import com.example.study.databinding.FragmentFoodDetailBinding
import com.example.study.model.FoodsModel

class FoodDetailFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)

        val food = arguments?.getParcelable<FoodsModel>("food")
        if (food != null) {

            binding.foodImg.setImageResource(food.foodImage)
            binding.foodName.text = food.foodName
            binding.foodRank.text = food.foodRank.toString()
            binding.foodPrice.text = food.foodPrice.toString()

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