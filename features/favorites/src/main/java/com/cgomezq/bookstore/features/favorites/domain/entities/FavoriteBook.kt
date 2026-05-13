package com.cgomezq.bookstore.features.favorites.domain.entities

data class FavoriteBook(
    val isbn: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val priceDisplay: String
)
