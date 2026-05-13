package com.cgomezq.bookstore.features.cart.domain.entities

data class CartItem(
    val isbn: Long,
    val title: String,
    val author: String,
    val coverUrl: String,
    val priceValue: Double,
    val priceCurrency: String,
    val priceDisplayValue: String,
    val quantity: Int
) {
    val totalPriceValue: Double get() = priceValue * quantity
}
