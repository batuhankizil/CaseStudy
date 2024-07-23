package com.example.study.adapter

import android.content.Context
import android.graphics.Color
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.study.MainActivity
import com.example.study.model.CategoryModel
import com.example.study.R
import com.example.study.databinding.ItemCategoryBinding
import com.example.study.databinding.ItemCategoryConstraintBinding

class CategoryAdapter(val categoryList: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCategoryConstraintBinding) : RecyclerView.ViewHolder(binding.root)

    private var selectedPosition = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  ItemCategoryConstraintBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]

        holder.binding.categoryName.text = category.categoryName
        holder.binding.icBurger.setImageResource(category.categoryIcon)

        holder.itemView.setBackgroundResource(R.drawable.category_item_background)

        if (position == selectedPosition) {
            holder.binding.categoryName.setTextColor(Color.WHITE)
        } else holder.binding.categoryName.setTextColor(Color.BLACK)

        holder.itemView.isSelected = position == selectedPosition

        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}