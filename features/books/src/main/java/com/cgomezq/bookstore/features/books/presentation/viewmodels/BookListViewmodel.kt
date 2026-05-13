package com.cgomezq.bookstore.features.books.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgomezq.bookstore.core.network.exceptions.NetworkException
import com.cgomezq.bookstore.features.books.domain.exceptions.EmptyBookListException
import com.cgomezq.bookstore.features.books.domain.usecases.GetBooks
import com.cgomezq.bookstore.features.books.presentation.contract.BookListIntent
import com.cgomezq.bookstore.features.books.presentation.contract.BookListState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform

class BookListViewmodel(
    private val getBooks: GetBooks
) : ViewModel() {
    private val intents = MutableSharedFlow<BookListIntent>()

    val uiState: StateFlow<BookListState> = intents
        .onSubscription { emit(BookListIntent.LoadBooks) }
        .transform { intent ->
            when (intent) {
                BookListIntent.LoadBooks -> try {
                    emit(BookListState.Loading)
                    emit(BookListState.ShowingBookList(books = getBooks()))
                } catch (e: NetworkException) {
                    emit(BookListState.ShowingError(e.message))
                } catch (_: EmptyBookListException) {
                    emit(BookListState.ShowingEmptyList)
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = BookListState.Loading
        )
}