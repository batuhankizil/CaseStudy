package com.example.study.adapter

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.databinding.ItemCategoryConstraintBinding
import com.example.study.databinding.ItemFoodsConstraintBinding
import com.example.study.databinding.ItemTitleBinding
import com.example.study.domain.CategoryUIModel
import com.example.study.domain.FoodsUIModel
import com.example.study.model.CategoryModel
import com.example.study.viewState.CategoryViewState
import com.example.study.viewState.FoodViewState


sealed class CollectiveModel {
    data class Category(val categoryModel: CategoryUIModel) : CollectiveModel()
    data class Food(val foodsModel: FoodsUIModel) : CollectiveModel()
}

class CollectiveAdapter(
    private val items: List<CollectiveModel>,
    private val onCategoryClick: (Int) -> Unit,
    private val onFoodClick: ((FoodsUIModel) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_FOOD = 0
        private const val VIEW_TYPE_CATEGORY = 1
    }

    class CategoryViewHolder(private val binding: ItemCategoryConstraintBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewState: CategoryViewState, onCategoryClick: (Int) -> Unit) {
            val categoryModel = viewState.categoryUIModel

            categoryModel.categoryIcon.let { imageUrl ->
                Glide.with(binding.categoryIcon.context)
                    .load(imageUrl)
                    .into(binding.categoryIcon)
            }
            binding.categoryName.text = categoryModel.categoryName

            binding.root.setBackgroundResource(R.drawable.category_item_background)

            binding.categoryName.setTextColor(viewState.getTextColor())
            viewState.getBackgroundResource()
                ?.let { binding.categoryItem.setBackgroundResource(it) }

            binding.root.setOnClickListener {
                viewState.updateSelection(categoryModel.id)
                onCategoryClick(categoryModel.id)
            }

        }
    }

    class FoodViewHolder(private val binding: ItemFoodsConstraintBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewState: FoodViewState, onFoodClick: (FoodsUIModel) -> Unit) {
            val foodsModel = viewState.foodsUIModel

            binding.foodRank.text = foodsModel.foodRank.toString()
            foodsModel.foodImage.let { imageUrl ->
                Glide.with(binding.foodImage.context)
                    .load(imageUrl)
                    .into(binding.foodImage)
            }
            binding.foodName.text = foodsModel.foodName
            binding.foodDetail.text = foodsModel.foodDetail

            val spannableString = SpannableString(foodsModel.foodPrice.toString())
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.foodPrice.text = spannableString

            val spannableStringDiscount = SpannableString(foodsModel.discountPrice.toString())
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.foodPriceDiscount.text = spannableStringDiscount

            binding.discount.visibility = viewState.getDiscountVisibility()
            binding.foodPriceDiscount.visibility = viewState.getDiscountVisibility()
            viewState.getFoodPriceTextColor()?.let { binding.foodPrice.setTextColor(it) }
            binding.foodPrice.paintFlags = viewState.getFoodPriceStrikethrough()
            binding.root.setOnClickListener { onFoodClick(foodsModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FOOD -> {
                val binding = ItemFoodsConstraintBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                FoodViewHolder(binding)
            }

            VIEW_TYPE_CATEGORY -> {
                val binding = ItemCategoryConstraintBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CategoryViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is CollectiveModel.Food -> onFoodClick?.let {
                (holder as FoodViewHolder).bind(
                    FoodViewState(item.foodsModel),
                    it
                )
            }

            is CollectiveModel.Category -> (holder as CategoryViewHolder).bind(
                CategoryViewState(item.categoryModel),
                onCategoryClick
            )

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CollectiveModel.Food -> VIEW_TYPE_FOOD
            is CollectiveModel.Category -> VIEW_TYPE_CATEGORY
        }
    }

    override fun getItemCount(): Int = items.size
}
