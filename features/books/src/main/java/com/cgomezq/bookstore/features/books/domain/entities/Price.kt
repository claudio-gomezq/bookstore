package com.cgomezq.bookstore.features.books.domain.entities

data class Price(
    val value: String,
    val currency: String,
    val displayValue: String
)