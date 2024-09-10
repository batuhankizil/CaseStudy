package com.example.study

import com.example.study.data.FoodsModelResponse
import com.example.study.domain.ProductDecider
import com.example.study.domain.mapper.ProductMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.Rule


@OptIn(ExperimentalCoroutinesApi::class)
class ProductMapperTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @InjectMockKs
    lateinit var productMapper: ProductMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { decider.decideDiscountPrice(any(), any()) } returns 1.0
    }

    @MockK
    private lateinit var decider: ProductDecider

    @Test
    fun `correctly maps foodRank to UI model properties`() =
        runTestAndCleanUp {
            // Given
            val foodResponse = FoodsModelResponse(
                foodRank = 10.0,
                foodName = "yok",
                foodDetail = "yok",
                foodPrice = 10.0,
                foodImage = null,
                id = 1,
                discount = false
            )

            // When
            val model = productMapper.mapFromResponse(foodResponse)

            //Then
            assertThat(model.foodRank).isEqualTo(10.0)
        }
}
