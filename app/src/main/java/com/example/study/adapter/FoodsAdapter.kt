package com.example.study.adapter

import android.graphics.Paint
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.study.databinding.ItemFoodsConstraintBinding
import com.example.study.model.FoodsModel

class FoodsAdapter(private val foodsList: List<FoodsModel>) :
    RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFoodsConstraintBinding) :
        RecyclerView.ViewHolder(binding.root)

    var onItemClick: ((FoodsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFoodsConstraintBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


        if (foods.discount) {
            holder.binding.discount.visibility = View.VISIBLE
            holder.binding.foodPriceDiscount.visibility = View.VISIBLE
            holder.binding.foodPrice.paintFlags = holder.binding.foodPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.binding.discount.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(foods)
        }

    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}