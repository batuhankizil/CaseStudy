package com.example.study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.adapter.CategoryAdapter
import com.example.study.adapter.FoodsAdapter
import com.example.study.databinding.ActivityMainBinding
import com.example.study.model.CategoryModel
import com.example.study.model.FoodsModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerCategory
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        val categoryList = ArrayList<CategoryModel>()

        categoryList.add(CategoryModel("Hamburger", R.drawable.burger))
        categoryList.add(CategoryModel("Pizza", R.drawable.pizza))
        categoryList.add(CategoryModel("Sandwich", R.drawable.sandwich))

        val adapter = CategoryAdapter(categoryList)

        recyclerView.adapter = adapter


        recyclerView = binding.recyclerFoods
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        val foodsList = ArrayList<FoodsModel>()

        foodsList.add(FoodsModel(4.8, R.drawable.img_chicken_burger, "Chicken burger", "200 gr chicken + cheese  Lettuce + tomato", 22.0000))
        foodsList.add(FoodsModel(4.5, R.drawable.img_chicken_burger, "Chicken burger", "200 gr chicken + cheese  Lettuce + tomato", 25.00))
        foodsList.add(FoodsModel(4.3, R.drawable.img_chicken_burger, "Chicken burger", "200 gr chicken + cheese  Lettuce + tomato", 25.00))

        val adapterFoods = FoodsAdapter(foodsList)

        recyclerView.adapter = adapterFoods


    }
}