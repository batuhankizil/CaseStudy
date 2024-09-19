package com.example.study.json

import com.example.study.domain.FoodsUIModel
import com.example.study.fragment.HomePageFragment
import com.google.gson.Gson

class JsonUtils {

    private val gson = Gson()

    fun foodToJson(food: FoodsUIModel): String {
        return gson.toJson(food)
    }
}