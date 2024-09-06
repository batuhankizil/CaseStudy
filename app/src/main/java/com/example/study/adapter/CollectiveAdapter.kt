package com.example.study.adapter

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.databinding.ItemCategoryConstraintBinding
import com.example.study.databinding.ItemFoodsConstraintBinding
import com.example.study.databinding.ItemTitleBinding
import com.example.study.domain.FoodsUIModel
import com.example.study.model.CategoryModel
import com.example.study.retrofit.Comment
import com.example.study.retrofit.Post


sealed class CollectiveModel {
    data class Category(val categoryModel: CategoryModel) : CollectiveModel()
    data class Food(val foodsModel: FoodsUIModel) : CollectiveModel()
    data class Post(val post: com.example.study.retrofit.Post, val comment: Comment) : CollectiveModel()
}

class CollectiveAdapter(
    private val items: List<CollectiveModel>,
    private val onCategoryClick: (Int) -> Unit,
    private val onFoodClick: ((FoodsUIModel) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_FOOD = 0
        private const val VIEW_TYPE_CATEGORY = 1
        private const val VIEW_TYPE_POST = 2
    }

    class PostViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, comment: Comment) {
            binding.title.text = post.title
            binding.comment.text = comment.text
        }
    }

    /*class RetrofitViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.title.text = post.title
        }
    }*/

    /*class CommentViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment.text = comment.text
        }
    }*/

    class CategoryViewHolder(private val binding: ItemCategoryConstraintBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryModel: CategoryModel, onCategoryClick: (Int) -> Unit) {
            binding.categoryName.text = categoryModel.categoryName
            binding.icBurger.setImageResource(categoryModel.categoryIcon)

            binding.root.setBackgroundResource(R.drawable.category_item_background)

            if (categoryModel.isSelected) {
                binding.categoryName.setTextColor(Color.WHITE)
                binding.categoryItem.setBackgroundResource(R.drawable.button_add_to_cart)
            } else {
                binding.categoryName.setTextColor(Color.BLACK)
            }

            binding.root.setOnClickListener { onCategoryClick(categoryModel.id) }
        }
    }

    class FoodViewHolder(private val binding: ItemFoodsConstraintBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodsModel: FoodsUIModel, onFoodClick: (FoodsUIModel) -> Unit) {
            binding.foodRank.text = foodsModel.foodRank.toString()
            foodsModel.foodImage.let { binding.foodImage.setImageResource(it) }
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

            if (foodsModel.discount) {
                binding.discount.visibility = View.VISIBLE
                binding.foodPrice.setTextColor(Color.GRAY)
                binding.foodPrice.paintFlags = binding.foodPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.foodPriceDiscount.visibility = View.VISIBLE
            } else {
                binding.discount.visibility = View.GONE
            }

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

            VIEW_TYPE_POST -> {
                val binding = ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PostViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is CollectiveModel.Food -> onFoodClick?.let {
                (holder as FoodViewHolder).bind(
                    item.foodsModel,
                    it
                )
            }

            is CollectiveModel.Category -> (holder as CategoryViewHolder).bind(
                item.categoryModel,
                onCategoryClick
            )

            is CollectiveModel.Post -> (holder as PostViewHolder).bind(item.post, item.comment)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CollectiveModel.Food -> VIEW_TYPE_FOOD
            is CollectiveModel.Category -> VIEW_TYPE_CATEGORY
            is CollectiveModel.Post -> VIEW_TYPE_POST
        }
    }

    override fun getItemCount(): Int = items.size
}
