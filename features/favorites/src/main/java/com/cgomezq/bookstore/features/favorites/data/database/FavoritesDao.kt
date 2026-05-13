package com.cgomezq.bookstore.features.favorites.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cgomezq.bookstore.features.favorites.data.entities.FavoriteBookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<FavoriteBookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteBookEntity)

    @Query("DELETE FROM favorites WHERE isbn = :isbn")
    suspend fun deleteFavorite(isbn: Long)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE isbn = :isbn)")
    suspend fun isFavorite(isbn: Long): Boolean
}
