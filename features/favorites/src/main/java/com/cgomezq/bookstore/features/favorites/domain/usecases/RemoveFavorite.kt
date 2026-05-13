package com.cgomezq.bookstore.features.favorites.domain.usecases

import com.cgomezq.bookstore.features.favorites.domain.repositories.FavoritesRepository

class RemoveFavorite(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(isbn: Long) = repository.removeFavorite(isbn)
}
