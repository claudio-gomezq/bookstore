package com.cgomezq.bookstore.features.cart.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val isbn: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val priceValue: Double,
    val priceCurrency: String,
    val priceDisplay: String,
    val quantity: Int
)
