package com.example.study

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.ViewModel.MainViewModel
import com.example.study.adapter.CategoryAdapter
import com.example.study.adapter.FoodsAdapter
import com.example.study.databinding.ActivityMainBinding
import com.example.study.databinding.ActivityMainConstraintBinding
import com.example.study.model.CategoryModel
import com.example.study.model.FoodsModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    //private lateinit var binding: ActivityMainBinding
    private lateinit var binding: ActivityMainConstraintBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = ActivityMainConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.init()

        binding.recyclerCategory.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        viewModel.getCategoryModelLiveData().observe(this@MainActivity) {
            val adapter =
                CategoryAdapter(it, onItemClick = { id -> viewModel.updateCategoryList(id) })
            binding.recyclerCategory.adapter = adapter
        }

        binding.recyclerFoods.layoutManager = GridLayoutManager(applicationContext, 2)
        val adapterFoods = FoodsAdapter(viewModel.getModels())
        binding.recyclerFoods.adapter = adapterFoods

        adapterFoods.onItemClick = {
            val intent = Intent(this, foodDetail::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }


    }
}