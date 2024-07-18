package com.example.study.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.study.model.CategoryModel
import com.example.study.R
import com.example.study.databinding.ItemCategoryBinding

class CategoryAdapter(private val categoryList: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]

        holder.binding.categoryName.text = category.categoryName
        holder.binding.categoryIcon.setImageResource(category.categoryIcon)

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}