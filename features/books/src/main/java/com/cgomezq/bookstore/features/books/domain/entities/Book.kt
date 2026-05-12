package com.cgomezq.bookstore.features.books.domain.entities

data class Book(
    val isbn: Long,
    val title: String,
    val author: String,
    val summary: String,
    val coverUrl: String,
    val price: Price
)