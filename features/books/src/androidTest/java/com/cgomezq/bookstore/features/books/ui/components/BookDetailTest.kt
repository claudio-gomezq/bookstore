package com.cgomezq.bookstore.features.books.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class BookDetailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testBook = Book(
        isbn = 1L,
        title = "Detailed Book",
        author = "Great Author",
        summary = "This is a very detailed summary of the book.",
        coverUrl = "",
        isFavorite = false,
        price = Price("15000", "CLP", "$15.000")
    )

    @Test
    fun itShouldDisplayBookDetails() {
        // When
        composeTestRule.setContent {
            BookDetail(
                book = testBook,
                addToFavorite = {},
                addToCart = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Detailed Book").assertIsDisplayed()
        composeTestRule.onNodeWithText("Great Author").assertIsDisplayed()
        composeTestRule.onNodeWithText("This is a very detailed summary of the book.").assertIsDisplayed()
        composeTestRule.onNodeWithText("$15.000").assertIsDisplayed()
    }

    @Test
    fun whenFavoriteIsClicked_itShouldNotifyCaller() {
        // Given
        var favoriteClicked = false

        // When
        composeTestRule.setContent {
            BookDetail(
                book = testBook,
                addToFavorite = { favoriteClicked = true },
                addToCart = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("favorite").performClick()

        // Then
        assertTrue(favoriteClicked)
    }

    @Test
    fun whenAddToCartIsClicked_itShouldNotifyCaller() {
        // Given
        var addToCartClicked = false

        // When
        composeTestRule.setContent {
            BookDetail(
                book = testBook,
                addToFavorite = {},
                addToCart = { addToCartClicked = true }
            )
        }
        composeTestRule.onNodeWithText("Add to cart").performClick()

        // Then
        assertTrue(addToCartClicked)
    }
}
