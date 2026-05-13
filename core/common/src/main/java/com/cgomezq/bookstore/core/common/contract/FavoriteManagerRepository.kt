package com.cgomezq.bookstore.core.common.contract

interface FavoriteManagerRepository {

    suspend fun toggleFavorite(
        isbn: Long,
        title: String,
        author: String,
        imageUrl: String,
        priceDisplay: String
    )

    suspend fun isFavorite(isbn: Long): Boolean
}