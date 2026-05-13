package com.cgomezq.bookstore.features.books.ui.contract

import androidx.compose.runtime.Stable
import com.cgomezq.bookstore.features.books.domain.entities.Book

@Stable
sealed interface BookListState {
    data object Loading: BookListState

    data class ShowingBookList(
        val books: List<Book>
    ): BookListState

    data object ShowingEmptyList: BookListState

    data class ShowingError(
        val message: String
    ): BookListState
}

@Stable
sealed interface BookListIntent {
    data object LoadBooks: BookListIntent

}