package com.cgomezq.bookstore.features.cart.presentation.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cgomezq.bookstore.features.cart.domain.entities.CartItem
import com.cgomezq.bookstore.features.cart.presentation.contract.CartState
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class CartScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showingCart_displaysItemsAndTotal() {
        val items = listOf(
            CartItem(1L, "Kotlin", "Author", "url", 10.0, "CLP", "$10", 1)
        )
        val totalPrice = "$10.00"

        composeTestRule.setContent {
            CartScreen(
                state = CartState.ShowingCart(items, totalPrice),
                onQuantityChange = { _, _ -> },
                onClearCart = {}
            )
        }

        composeTestRule.onNodeWithText("Kotlin").assertIsDisplayed()
        composeTestRule.onNodeWithText(totalPrice).assertIsDisplayed()
    }

    @Test
    fun clickingCheckout_triggersClearCartCallback() {
        val onClearCart: () -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            CartScreen(
                state = CartState.ShowingCart(emptyList(), "$0"),
                onQuantityChange = { _, _ -> },
                onClearCart = onClearCart
            )
        }
        composeTestRule.onNodeWithText("Checkout").performClick()

        verify { onClearCart() }
    }

    @Test
    fun emptyState_showsEmptyMessage() {
        composeTestRule.setContent {
            CartScreen(
                state = CartState.ShowingEmptyCart,
                onQuantityChange = { _, _ -> },
                onClearCart = {}
            )
        }

        composeTestRule.onNodeWithText("Your cart is empty").assertIsDisplayed()
    }
}
