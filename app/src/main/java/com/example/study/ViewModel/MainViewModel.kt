package com.example.study.ViewModel

import android.icu.util.ULocale.Category
import android.util.SparseBooleanArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.R
import com.example.study.model.CategoryModel
import com.example.study.model.FoodsModel
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class MainViewModel : ViewModel() {

    private val _categoryModel = MutableLiveData<List<CategoryModel>>()
    fun getCategoryModelLiveData(): LiveData<List<CategoryModel>> = _categoryModel

    init {
        fetchData()
    }

    fun fetchData() {
        fetchCategoryModel()
    }

    fun getModels(): List<FoodsModel> {
        return buildList {
            add(
                FoodsModel(
                    4.8,
                    R.drawable.chicken_burger,
                    "Chicken burger",
                    "200 gr chicken + cheese  Lettuce + tomato",
                    22.00,
                    1,
                    true
                )
            )
            add(
                FoodsModel(
                    4.5,
                    R.drawable.chese_burger,
                    "Chese burger",
                    "200 gr meat + Lettuce cheese + onion + tomato",
                    25.00,
                    2,
                    false
                )
            )
            add(
                FoodsModel(
                    4.3,
                    R.drawable.chicken_burger,
                    "Chicken burger",
                    "200 gr chicken + cheese  Lettuce + tomato",
                    25.00,
                    3,
                    false
                )
            )
            add(
                FoodsModel(
                    4.3,
                    R.drawable.chicken_burger,
                    "Chicken burger",
                    "200 gr chicken + cheese  Lettuce + tomato",
                    25.00,
                    4,
                    false
                )
            )
            add(
                FoodsModel(
                    4.3,
                    R.drawable.chese_burger,
                    "Chicken burger",
                    "200 gr chicken + cheese  Lettuce + tomato",
                    25.00,
                    5,
                    false
                )
            )
            add(
                FoodsModel(
                    4.3,
                    R.drawable.chese_burger,
                    "Chicken burger",
                    "200 gr chicken + cheese  Lettuce + tomato",
                    25.00,
                    6,
                    false
                )
            )

        }
    }

    private fun fetchCategoryModel() {
        _categoryModel.value = buildList {
            add(CategoryModel(1, "Hamburger", R.drawable.burger, true))
            add(CategoryModel(2, "Pizza", R.drawable.pizza, false))
            add(CategoryModel(3, "Sandwich", R.drawable.sandwich, false))
        }
    }

    fun updateCategoryList(selectedItemId: Int) {
        _categoryModel.value = _categoryModel.value?.map {
            if (it.id == selectedItemId) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
    }





}