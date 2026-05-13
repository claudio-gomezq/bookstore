package com.cgomezq.bookstore.features.favorites.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cgomezq.bookstore.features.favorites.data.entities.FavoriteBookEntity

@Database(entities = [FavoriteBookEntity::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}
