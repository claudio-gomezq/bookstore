package com.cgomezq.bookstore.features.cart.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cgomezq.bookstore.features.cart.domain.entities.CartItem
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class CartItemRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cartItemRow_displaysItemInfo() {
        val item = CartItem(
            isbn = 1L,
            title = "Kotlin Programming",
            author = "JetBrains",
            coverUrl = "url",
            priceValue = 10000.0,
            priceCurrency = "CLP",
            priceDisplayValue = "$10.000",
            quantity = 2
        )

        composeTestRule.setContent {
            CartItemRow(
                item = item,
                onQuantityChange = {}
            )
        }

        composeTestRule.onNodeWithText("Kotlin Programming").assertIsDisplayed()
        composeTestRule.onNodeWithText("JetBrains").assertIsDisplayed()
        composeTestRule.onNodeWithText("$10.000").assertIsDisplayed()
        composeTestRule.onNodeWithText("2").assertIsDisplayed()
    }

    @Test
    fun clickingIncrease_callsOnQuantityChange() {
        val item = CartItem(1L, "T", "A", "U", 1.0, "C", "D", 1)
        val onQuantityChange: (Int) -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            CartItemRow(item = item, onQuantityChange = onQuantityChange)
        }

        // Using content description from strings.xml
        composeTestRule.onNodeWithContentDescription("Increase quantity").performClick()

        verify { onQuantityChange(2) }
    }

    @Test
    fun clickingDecrease_callsOnQuantityChange() {
        val item = CartItem(1L, "T", "A", "U", 1.0, "C", "D", 2)
        val onQuantityChange: (Int) -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            CartItemRow(item = item, onQuantityChange = onQuantityChange)
        }

        composeTestRule.onNodeWithContentDescription("Decrease quantity").performClick()

        verify { onQuantityChange(1) }
    }
}
