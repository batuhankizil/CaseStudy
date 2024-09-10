package com.example.study

import com.example.study.domain.ProductDecider
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ProductDeciderTest {


    @Test
    fun `decideDiscountPrice should show the reduced price when a discount is present`() {
        // Given
        val decider = ProductDecider()

        // When
        val salePrice = decider.decideDiscountPrice(100.0, true)

        // Then
        assertThat(salePrice).isEqualTo(90)
    }

    @Test
    fun `decideDiscountPrice should return orginal price when discount is not available`() {
        // Given
        val decider = ProductDecider()

        // When
        val salePrice = decider.decideDiscountPrice(100.0, false)

        // Then
        assertThat(salePrice).isEqualTo(salePrice)
    }

}