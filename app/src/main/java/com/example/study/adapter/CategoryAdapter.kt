package com.example.study.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.SearchViewBindingAdapter.OnSuggestionClick
import androidx.recyclerview.widget.RecyclerView
import com.example.study.model.CategoryModel
import com.example.study.R
import com.example.study.databinding.ItemCategoryConstraintBinding

class CategoryAdapter(
    val categoryList: List<CategoryModel>,
    val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCategoryConstraintBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryConstraintBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]

        holder.binding.categoryName.text = category.categoryName
        holder.binding.icBurger.setImageResource(category.categoryIcon)

        holder.itemView.setBackgroundResource(R.drawable.category_item_background)

        if (category.isSelected) {
            holder.binding.categoryName.setTextColor(Color.WHITE)
        } else holder.binding.categoryName.setTextColor(Color.BLACK)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(category.id)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}