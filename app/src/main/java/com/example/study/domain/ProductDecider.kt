package com.example.study.domain

import javax.inject.Inject

class ProductDecider @Inject constructor() {

    fun decideDiscountPrice(salePrice: Double, hasDiscount: Boolean): Double {
        val discountRate = 0.10
        val discountedPrice = if (hasDiscount) {
            salePrice * (1 - discountRate)
        } else {
            salePrice
        }

        return discountedPrice

    }


}