package com.cgomezq.bookstore

import androidx.annotation.DrawableRes
import com.cgomezq.bookstore.features.books.ui.navigation.BooksDestinations
import com.cgomezq.bookstore.features.favorites.presentation.navigation.FavoritesDestination
import com.cgomezq.bookstore.designsystem.R

enum class MainDestinations(
    val destination: Any,
    val label: String,
    @param:DrawableRes val icon: Int
) {
    Books(
        destination = BooksDestinations.BookList,
        label = "Catalog",
        icon = R.drawable.ic_book
    ),
    Favorites(
        destination = FavoritesDestination,
        label = "Favorites",
        icon = R.drawable.ic_favorite
    ),
    Cart(
        destination = BooksDestinations.BookList,
        label = "Cart",
        icon = R.drawable.ic_shopping_cart
    )
}