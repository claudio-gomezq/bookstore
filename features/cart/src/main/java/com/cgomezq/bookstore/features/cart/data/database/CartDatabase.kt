package com.cgomezq.bookstore.features.cart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cgomezq.bookstore.features.cart.data.entities.CartItemEntity

@Database(entities = [CartItemEntity::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
