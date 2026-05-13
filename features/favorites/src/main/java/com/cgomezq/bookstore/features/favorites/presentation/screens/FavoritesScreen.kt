package com.cgomezq.bookstore.features.favorites.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cgomezq.bookstore.features.favorites.R
import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook
import com.cgomezq.bookstore.features.favorites.presentation.components.FavoriteList
import com.cgomezq.bookstore.features.favorites.presentation.contract.FavoritesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onBookClick: (FavoriteBook) -> Unit,
    onRemoveFavorite: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.favorites_title)) })
        },
        contentWindowInsets = WindowInsets()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (state) {
                FavoritesState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                            .testTag("LoadingIndicator")
                    )
                }

                is FavoritesState.ShowingFavorites -> {
                    FavoriteList(
                        favorites = state.books,
                        onFavoriteClick = onBookClick,
                        onRemoveFavorite = onRemoveFavorite
                    )
                }

                FavoritesState.ShowingEmptyList -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.favorites_no_favorites_available)
                    )
                }

                is FavoritesState.ShowingError -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = state.message
                    )
                }
            }
        }
    }
}
