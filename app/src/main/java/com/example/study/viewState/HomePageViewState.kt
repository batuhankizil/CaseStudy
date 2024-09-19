package com.example.study.viewState

import com.example.study.ViewModel.MainViewModel
import javax.inject.Inject

data class HomePageViewState(val username: String? = null) {
    @Inject
    constructor() : this(null)
}