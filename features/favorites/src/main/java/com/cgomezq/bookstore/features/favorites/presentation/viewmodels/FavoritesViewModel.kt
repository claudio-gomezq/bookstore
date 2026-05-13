package com.cgomezq.bookstore.features.favorites.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgomezq.bookstore.features.favorites.domain.usecases.GetFavorites
import com.cgomezq.bookstore.features.favorites.domain.usecases.RemoveFavorite
import com.cgomezq.bookstore.features.favorites.presentation.contract.FavoritesState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    getFavorites: GetFavorites,
    private val removeFavorite: RemoveFavorite
) : ViewModel() {
    val uiState: StateFlow<FavoritesState> = getFavorites()
        .map { books ->
            if (books.isEmpty()) FavoritesState.ShowingEmptyList
            else FavoritesState.ShowingFavorites(books)
        }
        .catch { emit(FavoritesState.ShowingError(it.message ?: "Error")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavoritesState.Loading
        )

    fun onRemoveFavorite(isbn: Long) {
        viewModelScope.launch {
            removeFavorite(isbn)
        }
    }
}
