package com.cgomezq.bookstore.features.favorites.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteBookEntity(
    @PrimaryKey val isbn: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val priceDisplay: String
)
