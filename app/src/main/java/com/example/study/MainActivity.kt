package com.example.study

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
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

    //private lateinit var binding: ActivityMainBinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomePageFragment())

    }

    private fun replaceFragment(homePageFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, homePageFragment).commit()
    }
}