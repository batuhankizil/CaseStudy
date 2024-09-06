package com.example.study.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.ViewModel.MainViewModel
import com.example.study.adapter.CollectiveAdapter
import com.example.study.adapter.CollectiveModel
import com.example.study.databinding.FragmentRetrofitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitFragment : Fragment() {

    val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentRetrofitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)

        binding.recyclerRetrofit.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewModel.getPostsWithComments().observe(viewLifecycleOwner) { posts ->

                val adapter = CollectiveAdapter(items = posts,
                    onCategoryClick = {})
                binding.recyclerRetrofit.adapter = adapter

        }



        return binding.root
    }
}
