package com.cgomezq.bookstore.features.books.ui.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cgomezq.bookstore.features.books.ui.screens.BookListScreen
import com.cgomezq.bookstore.features.books.ui.viewmodels.BookListViewmodel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.booksNavigation(navController: NavController){
    composable<BooksDestinations.BookList> {
        val viewmodel = koinViewModel<BookListViewmodel>()
        val state by viewmodel.uiState.collectAsStateWithLifecycle()
        BookListScreen(
            state = state,
            onBookClick = {
                navController.navigate(BooksDestinations.BookDetail(it.isbn))
            }
        )
    }
    composable<BooksDestinations.BookDetail> {
    }
}