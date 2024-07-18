package com.example.study.adapter

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.databinding.ItemFoodsBinding
import com.example.study.model.FoodsModel
import java.util.zip.Inflater

class FoodsAdapter(private val foodsList: ArrayList<FoodsModel>) :
    RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemFoodsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foods = foodsList[position]

        holder.binding.foodRank.text = foods.foodRank.toString()
        holder.binding.foodImage.setImageResource(foods.foodImage)
        holder.binding.foodName.text = foods.foodName
        holder.binding.foodDetail.text = foods.foodDetail
        holder.binding.foodPrice.text = foods.foodPrice.toString()

        val spannableString = SpannableString(foods.foodPrice.toString())
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.foodPrice.text = spannableString



    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}