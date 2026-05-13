package com.cgomezq.bookstore.features.favorites.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cgomezq.bookstore.designsystem.components.BookstoreListItem
import com.cgomezq.bookstore.designsystem.R as DesignSystemR
import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook

@Composable
fun FavoriteList(
    favorites: List<FavoriteBook>,
    onFavoriteClick: (FavoriteBook) -> Unit,
    onRemoveFavorite: (Long) -> Unit
) {
    LazyColumn {
        items(favorites) { favorite ->
            Column {
                FavoriteItem(
                    favorite = favorite,
                    onClick = { onFavoriteClick(favorite) },
                    onRemoveFavorite = { onRemoveFavorite(favorite.isbn) }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun FavoriteItem(
    favorite: FavoriteBook,
    onClick: () -> Unit,
    onRemoveFavorite: () -> Unit
) {
    BookstoreListItem(
        modifier = Modifier.fillMaxWidth(),
        title = favorite.title,
        subtitle = favorite.author,
        price = favorite.priceDisplay,
        imageUrl = favorite.imageUrl,
        onClick = onClick,
        trailingContent = {
            IconButton(onClick = onRemoveFavorite) {
                Icon(
                    painter = painterResource(DesignSystemR.drawable.ic_favorite),
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    )
}
