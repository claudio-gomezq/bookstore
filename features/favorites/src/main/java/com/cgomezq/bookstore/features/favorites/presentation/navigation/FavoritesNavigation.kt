package com.cgomezq.bookstore.features.favorites.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cgomezq.bookstore.features.favorites.presentation.screens.FavoritesScreen
import com.cgomezq.bookstore.features.favorites.presentation.viewmodels.FavoritesViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object FavoritesDestination

fun NavGraphBuilder.favoritesNavigation(
    onNavigateToBookDetail: (Long) -> Unit
) {
    composable<FavoritesDestination> {
        val viewmodel = koinViewModel<FavoritesViewModel>()
        val state by viewmodel.uiState.collectAsStateWithLifecycle()
        FavoritesScreen(
            state = state,
            onBookClick = {
                onNavigateToBookDetail(it.isbn)
            },
            onRemoveFavorite = viewmodel::onRemoveFavorite
        )
    }
}
