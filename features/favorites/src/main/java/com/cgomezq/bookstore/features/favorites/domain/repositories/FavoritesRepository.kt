package com.cgomezq.bookstore.features.favorites.domain.repositories

import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getFavorites(): Flow<List<FavoriteBook>>

    suspend fun removeFavorite(isbn: Long)
}
