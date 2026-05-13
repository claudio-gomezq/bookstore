package com.cgomezq.bookstore.features.books.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price
import com.cgomezq.bookstore.features.books.ui.contract.BookListState
import org.junit.Rule
import org.junit.Test

class BookListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenStateIsLoading_itShouldShowLoadingIndicator() {
        // When
        composeTestRule.setContent {
            BookListScreen(state = BookListState.Loading, onBookClick = {})
        }

        // Then
        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()
        composeTestRule.onNodeWithText("Catalog").assertIsDisplayed()
    }

    @Test
    fun whenStateIsShowingBookList_itShouldShowBooks() {
        // Given
        val books = listOf(
            Book(
                isbn = 1L,
                title = "Screen Book",
                author = "Screen Author",
                summary = "Summary",
                coverUrl = "",
                isFavorite = false,
                price = Price("10000", "CLP", "$10.000")
            )
        )

        // When
        composeTestRule.setContent {
            BookListScreen(
                state = BookListState.ShowingBookList(books),
                onBookClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Screen Book").assertIsDisplayed()
        composeTestRule.onNodeWithText("Screen Author").assertIsDisplayed()
    }

    @Test
    fun whenStateIsShowingError_itShouldShowErrorMessage() {
        // Given
        val errorMessage = "Failed to load books"

        // When
        composeTestRule.setContent {
            BookListScreen(
                state = BookListState.ShowingError(errorMessage),
                onBookClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun whenStateIsShowingEmptyList_itShouldShowEmptyMessage() {
        // When
        composeTestRule.setContent {
            BookListScreen(
                state = BookListState.ShowingEmptyList,
                onBookClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("No books available").assertIsDisplayed()
    }
}
