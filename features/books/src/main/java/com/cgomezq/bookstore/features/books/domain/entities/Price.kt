package com.cgomezq.bookstore.features.books.domain.entities

data class Price(
    val value: Double,
    val currency: String,
    val displayValue: String
)