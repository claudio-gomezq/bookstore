package com.cgomezq.bookstore.features.books.ui.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.cgomezq.bookstore.features.books.ui.screens.BookDetailScreen
import com.cgomezq.bookstore.features.books.ui.screens.BookListScreen
import com.cgomezq.bookstore.features.books.ui.viewmodels.BookDetailViewmodel
import com.cgomezq.bookstore.features.books.ui.viewmodels.BookListViewmodel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.booksNavigation(navController: NavController) {
    bookListNavigation(navController)
    bookDetailNavigation()
}

fun NavGraphBuilder.bookListNavigation(navController: NavController) {
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
}

fun NavGraphBuilder.bookDetailNavigation() {
    composable<BooksDestinations.BookDetail> {
        val route = it.toRoute<BooksDestinations.BookDetail>()
        val viewmodel = koinViewModel<BookDetailViewmodel>(
            parameters = { parametersOf(route.isbn) }
        )
        val state by viewmodel.uiState.collectAsStateWithLifecycle()
        BookDetailScreen(
            state = state,
            emitIntent = viewmodel::emitIntent
        )
    }
}
