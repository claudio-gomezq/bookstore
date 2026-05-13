package com.cgomezq.bookstore.features.books.ui.contract

import androidx.compose.runtime.Stable
import com.cgomezq.bookstore.features.books.domain.entities.Book

@Stable
sealed interface BookDetailState {
    data object Loading: BookDetailState

    data class ShowingBookDetail(
        val book: Book
    ): BookDetailState

    data class ShowingError(
        val message: String
    ): BookDetailState
}

@Stable
sealed interface BookDetailIntent {
    data object LoadBookDetail: BookDetailIntent
    data class AddToCart(
        val book: Book
    ): BookDetailIntent
    data class AddToFavorite(
        val book: Book
    ): BookDetailIntent

}