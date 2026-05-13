package com.cgomezq.bookstore.features.cart.domain.entities

data class CartItem(
    val isbn: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val priceValue: Double,
    val priceCurrency: String,
    val priceDisplay: String,
    val quantity: Int
) {
    val totalPriceValue: Double get() = priceValue * quantity
}
