package com.cgomezq.bookstore.features.books.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cgomezq.bookstore.features.books.R
import com.cgomezq.bookstore.features.books.presentation.components.BookDetail
import com.cgomezq.bookstore.features.books.presentation.contract.BookDetailIntent
import com.cgomezq.bookstore.features.books.presentation.contract.BookDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    state: BookDetailState,
    emitIntent: (BookDetailIntent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.books_detail_title)) })
        },
        contentWindowInsets = WindowInsets()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (state) {
                BookDetailState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    )
                }

                is BookDetailState.ShowingBookDetail -> {
                    BookDetail(
                        book = state.book,
                        addToFavorite = {
                            emitIntent(BookDetailIntent.AddToFavorite(state.book))
                        },
                        addToCart = {
                            emitIntent(BookDetailIntent.AddToCart(state.book))
                        }
                    )
                }

                is BookDetailState.ShowingError -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = state.message
                    )
                }

            }
        }
    }
}