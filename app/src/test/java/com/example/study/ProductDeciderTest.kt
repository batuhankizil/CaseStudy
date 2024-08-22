package com.example.study

import com.example.study.domain.ProductDecider
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ProductDeciderTest {

    @Test
    fun `discount available`() {
        val decider = ProductDecider()

        val salePrice = decider.decideDiscountPrice(100.0, true)

        assertThat(salePrice).isEqualTo(90)
    }

    @Test
    fun `discount not available`() {
        val decider = ProductDecider()

        val salePrice = decider.decideDiscountPrice(100.0, false)

        assertThat(salePrice).isEqualTo(salePrice)
    }

}