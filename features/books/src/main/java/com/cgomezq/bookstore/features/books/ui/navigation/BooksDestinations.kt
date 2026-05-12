package com.cgomezq.bookstore.features.books.ui.navigation

import kotlinx.serialization.Serializable

sealed interface BooksDestinations {

    @Serializable
    data object BookList

    @Serializable
    data class BookDetail(
        val isbn: Long
    )
}