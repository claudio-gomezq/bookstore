package com.cgomezq.bookstore.features.cart.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class CartSummaryTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cartSummary_displaysTotalPrice() {
        val totalPrice = "$20.000"

        composeTestRule.setContent {
            CartSummary(
                totalPrice = totalPrice,
                onClearCart = {}
            )
        }

        composeTestRule.onNodeWithText(totalPrice).assertIsDisplayed()
        composeTestRule.onNodeWithText("Total:").assertIsDisplayed()
    }

    @Test
    fun clickingCheckout_callsOnClearCart() {
        val onClearCart: () -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            CartSummary(
                totalPrice = "$0",
                onClearCart = onClearCart
            )
        }

        composeTestRule.onNodeWithText("Checkout").performClick()

        verify { onClearCart() }
    }
}
