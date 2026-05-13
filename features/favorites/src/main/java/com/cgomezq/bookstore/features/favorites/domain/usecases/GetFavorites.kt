package com.cgomezq.bookstore.features.favorites.domain.usecases

import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook
import com.cgomezq.bookstore.features.favorites.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetFavorites(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<FavoriteBook>> = repository.getFavorites()
}
