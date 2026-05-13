package com.cgomezq.bookstore.features.favorites.presentation.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook
import com.cgomezq.bookstore.features.favorites.presentation.contract.FavoritesState
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class FavoritesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState_showsLoadingIndicator() {
        composeTestRule.setContent {
            FavoritesScreen(
                state = FavoritesState.Loading,
                onBookClick = {},
                onRemoveFavorite = {}
            )
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()
    }

    @Test
    fun emptyState_showsNoFavoritesText() {
        composeTestRule.setContent {
            FavoritesScreen(
                state = FavoritesState.ShowingEmptyList,
                onBookClick = {},
                onRemoveFavorite = {}
            )
        }
        composeTestRule.onNodeWithText("No favorite books yet").assertIsDisplayed()
    }

    @Test
    fun errorState_showsErrorMessage() {
        val errorMessage = "Something went wrong"
        composeTestRule.setContent {
            FavoritesScreen(
                state = FavoritesState.ShowingError(errorMessage),
                onBookClick = {},
                onRemoveFavorite = {}
            )
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun showingFavorites_showsList() {
        val books = listOf(
            FavoriteBook(1L, "Kotlin In Action", "Dmitry Jemerov", "url", "$40"),
            FavoriteBook(2L, "Clean Code", "Robert C. Martin", "url", "$35")
        )

        composeTestRule.setContent {
            FavoritesScreen(
                state = FavoritesState.ShowingFavorites(books),
                onBookClick = {},
                onRemoveFavorite = {}
            )
        }

        composeTestRule.onNodeWithText("Kotlin In Action").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clean Code").assertIsDisplayed()
    }

    @Test
    fun clickingBook_triggersCallback() {
        val book = FavoriteBook(1L, "Kotlin In Action", "Dmitry Jemerov", "url", "$40")
        val onBookClick: (FavoriteBook) -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            FavoritesScreen(
                state = FavoritesState.ShowingFavorites(listOf(book)),
                onBookClick = onBookClick,
                onRemoveFavorite = {}
            )
        }

        composeTestRule.onNodeWithText("Kotlin In Action").performClick()

        verify { onBookClick(book) }
    }
}
