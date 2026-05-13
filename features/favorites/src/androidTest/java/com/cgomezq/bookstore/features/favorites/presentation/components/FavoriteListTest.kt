package com.cgomezq.bookstore.features.favorites.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class FavoriteListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun favoriteList_displaysAllItems() {
        val favorites = listOf(
            FavoriteBook(1L, "Title 1", "Author 1", "url1", "$10"),
            FavoriteBook(2L, "Title 2", "Author 2", "url2", "$20")
        )

        composeTestRule.setContent {
            FavoriteList(
                favorites = favorites,
                onFavoriteClick = {},
                onRemoveFavorite = {}
            )
        }

        composeTestRule.onNodeWithText("Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Title 2").assertIsDisplayed()
    }

    @Test
    fun clickingRemove_triggersCallback() {
        val favorite = FavoriteBook(1L, "Title 1", "Author 1", "url1", "$10")
        val onRemoveFavorite: (Long) -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            FavoriteList(
                favorites = listOf(favorite),
                onFavoriteClick = {},
                onRemoveFavorite = onRemoveFavorite
            )
        }

        composeTestRule.onNodeWithTag("RemoveFavoriteButton_1").performClick()

        verify { onRemoveFavorite(1L) }
    }
}
