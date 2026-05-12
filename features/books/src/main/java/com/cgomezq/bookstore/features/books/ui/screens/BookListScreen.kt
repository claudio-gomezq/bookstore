package com.cgomezq.bookstore.features.books.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.ui.components.BookList
import com.cgomezq.bookstore.features.books.ui.contract.BookListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    state: BookListState,
    onBookClick: (Book) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catalog") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (state) {
                BookListState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    )
                }

                is BookListState.ShowingBookList -> {
                    BookList(books = state.books, onBookClick = onBookClick)
                }

                BookListState.ShowingEmptyList -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "No books available"
                    )
                }

                is BookListState.ShowingError -> {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = state.message
                    )
                }
            }
        }
    }
}