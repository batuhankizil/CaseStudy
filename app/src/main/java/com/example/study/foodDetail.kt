package com.example.study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.study.databinding.ActivityFoodDetailConstraintBinding
import com.example.study.model.FoodsModel

class foodDetail : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailConstraintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val food = intent.getParcelableExtra<FoodsModel>("food")
        if (food != null) {

            binding.foodImg.setImageResource(food.foodImage)
            binding.foodName.text = food.foodName
            binding.foodRank.text = food.foodRank.toString()
            binding.foodPrice.text = food.foodPrice.toString()

        }
    }
}