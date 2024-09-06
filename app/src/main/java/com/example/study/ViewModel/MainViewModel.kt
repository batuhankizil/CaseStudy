package com.example.study.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.study.R
import com.example.study.adapter.CollectiveModel
import com.example.study.domain.usecase.ProductUseCase
import com.example.study.model.CategoryModel
import com.example.study.domain.FoodsUIModel
import com.example.study.retrofit.Comment
import com.example.study.retrofit.DataRepository
import com.example.study.retrofit.Post
import com.example.study.sharedPreferences.LoginDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val loginDataSource: LoginDataSource,
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _categoryModel = MutableLiveData<List<CategoryModel>>()
    fun getCategoryModelLiveData(): LiveData<List<CategoryModel>> = _categoryModel

    private val _foodItems = MutableStateFlow<List<FoodsUIModel>>(emptyList())
    fun getFoodsModelStateFlow(): Flow<List<FoodsUIModel>> = _foodItems

    private val _username = MutableLiveData<String?>()
    val username: LiveData<String?> get() = _username

    private val _posts = MutableLiveData<List<CollectiveModel>>()
    fun getPostsWithComments(): LiveData<List<CollectiveModel>> = _posts

    init {
        fetchCategoryModel()
        loadFoodItems()
        loadUsername()
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            val posts = dataRepository.getPosts()
            val comments = dataRepository.getComments()

            val commentsByPostId = comments.groupBy { it.postId }

            val collectiveItems = posts.map { post ->
                val postComments = commentsByPostId[post.id]?.firstOrNull()
                CollectiveModel.Post(post, postComments ?: Comment(post.id, "No comments", post.id))
            }

            _posts.value = collectiveItems
        }
    }


    private fun loadFoodItems() {
        viewModelScope.launch {
            _foodItems.value = productUseCase.fetchProducts()
        }
    }

    private fun loadUsername() {
        _username.value = loginDataSource.userName
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