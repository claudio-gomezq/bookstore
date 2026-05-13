package com.cgomezq.bookstore.features.books.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BookModel(
    val id: Long,
    val title: String,
    val author: String,
    val image: String,
    val summary: String,
    val priceCurrency: String,
    val priceValue: Int,
    val priceDisplayValue: String
)