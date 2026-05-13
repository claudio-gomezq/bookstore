package com.cgomezq.bookstore.features.favorites.data.repositories

import com.cgomezq.bookstore.core.common.contract.FavoriteManagerRepository
import com.cgomezq.bookstore.features.favorites.data.database.FavoritesDao
import com.cgomezq.bookstore.features.favorites.data.entities.FavoriteBookEntity
import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook
import com.cgomezq.bookstore.features.favorites.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ActualFavoritesRepository(
    private val favoritesDao: FavoritesDao
) : FavoritesRepository, FavoriteManagerRepository {

    override fun getFavorites(): Flow<List<FavoriteBook>> {
        return favoritesDao.getFavorites().map { entities ->
            entities.map { it.toFavoriteBook() }
        }
    }

    override suspend fun removeFavorite(isbn: Long) {
        favoritesDao.deleteFavorite(isbn)
    }

    override suspend fun toggleFavorite(
        isbn: Long,
        title: String,
        author: String,
        imageUrl: String,
        priceDisplay: String
    ) {
        if (isFavorite(isbn)) {
            removeFavorite(isbn)
        } else {
            favoritesDao.insertFavorite(
                FavoriteBookEntity(
                    isbn = isbn,
                    title = title,
                    author = author,
                    imageUrl = imageUrl,
                    priceDisplay = priceDisplay
                )
            )
        }
    }

    override suspend fun isFavorite(isbn: Long): Boolean {
        return favoritesDao.isFavorite(isbn)
    }

    private fun FavoriteBookEntity.toFavoriteBook() = FavoriteBook(
        isbn = isbn,
        title = title,
        author = author,
        imageUrl = imageUrl,
        priceDisplay = priceDisplay
    )
}
