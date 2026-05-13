package com.cgomezq.bookstore

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.cgomezq.bookstore.features.books.presentation.navigation.BooksDestinations
import com.cgomezq.bookstore.features.books.presentation.navigation.bookDetailNavigation
import com.cgomezq.bookstore.features.books.presentation.navigation.booksNavigation
import com.cgomezq.bookstore.features.cart.presentation.navigation.CartDestination
import com.cgomezq.bookstore.features.cart.presentation.navigation.cartNavigation
import com.cgomezq.bookstore.features.favorites.presentation.navigation.FavoritesDestination
import com.cgomezq.bookstore.features.favorites.presentation.navigation.favoritesNavigation

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.Catalog,
    ) {
        navigation<Destinations.Catalog>(startDestination = BooksDestinations.BookList) {
            booksNavigation(navController)
        }

        navigation<Destinations.Favorites>(startDestination = FavoritesDestination) {
            favoritesNavigation(
                onNavigateToBookDetail = { isbn ->
                    navController.navigate(BooksDestinations.BookDetail(isbn))
                }
            )
            bookDetailNavigation(navController)
        }

        navigation<Destinations.Cart>(startDestination = CartDestination) {
            cartNavigation()
        }

    }
}