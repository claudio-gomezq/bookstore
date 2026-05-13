package com.cgomezq.bookstore.core.common.contract

interface CartManagerRepository {
    suspend fun addToCart(
        isbn: Long,
        title: String,
        author: String,
        imageUrl: String,
        priceValue: Double,
        priceCurrency: String,
        priceDisplay: String
    )
}
