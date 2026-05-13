package com.cgomezq.bookstore.features.books.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenListIsNotEmpty_itShouldShowBooks() {
        // Given
        val books = listOf(
            Book(
                isbn = 1L,
                title = "Book Title 1",
                author = "Author 1",
                summary = "Summary 1",
                coverUrl = "",
                isFavorite = false,
                price = Price("10000", "CLP", "$10.000")
            ),
            Book(
                isbn = 2L,
                title = "Book Title 2",
                author = "Author 2",
                summary = "Summary 2",
                coverUrl = "",
                isFavorite = false,
                price = Price("20000", "CLP", "$20.000")
            )
        )

        // When
        composeTestRule.setContent {
            BookList(books = books, onBookClick = {})
        }

        // Then
        composeTestRule.onNodeWithText("Book Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Author 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Book Title 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Author 2").assertIsDisplayed()
    }

    @Test
    fun whenBookIsClicked_itShouldNotifyCaller() {
        // Given
        var clickedBook: Book? = null
        val books = listOf(
            Book(
                isbn = 1L,
                title = "Book Title 1",
                author = "Author 1",
                summary = "Summary 1",
                coverUrl = "",
                isFavorite = false,
                price = Price("10000", "CLP", "$10.000")
            )
        )

        // When
        composeTestRule.setContent {
            BookList(books = books, onBookClick = { clickedBook = it })
        }
        composeTestRule.onNodeWithText("Book Title 1").performClick()

        // Then
        assertEquals(books[0], clickedBook)
    }
}
