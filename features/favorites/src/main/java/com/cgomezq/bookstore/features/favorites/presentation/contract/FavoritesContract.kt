package com.cgomezq.bookstore.features.favorites.presentation.contract

import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook

sealed interface FavoritesState {
    data object Loading : FavoritesState
    data class ShowingFavorites(val books: List<FavoriteBook>) : FavoritesState
    data object ShowingEmptyList : FavoritesState
    data class ShowingError(val message: String) : FavoritesState
}