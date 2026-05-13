package com.cgomezq.bookstore.features.books.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgomezq.bookstore.core.common.contract.FavoriteManagerRepository
import com.cgomezq.bookstore.core.common.contract.CartManagerRepository
import com.cgomezq.bookstore.core.network.exceptions.NetworkException
import com.cgomezq.bookstore.features.books.domain.usecases.GetBookDetail
import com.cgomezq.bookstore.features.books.ui.contract.BookDetailIntent
import com.cgomezq.bookstore.features.books.ui.contract.BookDetailState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class BookDetailViewmodel(
    private val getBookDetail: GetBookDetail,
    private val favoriteManagerRepository: FavoriteManagerRepository,
    private val cartManagerRepository: CartManagerRepository,
    private val isbn: Long,
) : ViewModel() {
    private val intents = MutableSharedFlow<BookDetailIntent>()

    val uiState: StateFlow<BookDetailState> = intents
        .onSubscription { emit(BookDetailIntent.LoadBookDetail) }
        .transform { intent ->
            when (intent) {
                BookDetailIntent.LoadBookDetail -> try {
                    emit(BookDetailState.Loading)
                    val book = getBookDetail(isbn)
                    val isFavorite = favoriteManagerRepository.isFavorite(isbn)
                    emit(BookDetailState.ShowingBookDetail(book = book.copy(isFavorite = isFavorite)))
                } catch (e: NetworkException) {
                    emit(BookDetailState.ShowingError(e.message))
                }

                is BookDetailIntent.AddToFavorite -> {
                    val book = intent.book
                    favoriteManagerRepository.toggleFavorite(
                        isbn = book.isbn,
                        title = book.title,
                        author = book.author,
                        imageUrl = book.coverUrl,
                        priceDisplay = book.price.displayValue
                    )
                    val isFavorite = favoriteManagerRepository.isFavorite(book.isbn)
                    emit(BookDetailState.ShowingBookDetail(book = book.copy(isFavorite = isFavorite)))
                }

                is BookDetailIntent.AddToCart -> {
                    val book = intent.book
                    cartManagerRepository.addToCart(
                        isbn = book.isbn,
                        title = book.title,
                        author = book.author,
                        imageUrl = book.coverUrl,
                        priceValue = book.price.value,
                        priceCurrency = book.price.currency,
                        priceDisplay = book.price.displayValue
                    )
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = BookDetailState.Loading
        )

    fun emitIntent(intent: BookDetailIntent) {
        viewModelScope.launch {
            intents.emit(intent)
        }
    }
}
