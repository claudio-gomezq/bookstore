package com.cgomezq.bookstore.features.cart.domain.repositories

import com.cgomezq.bookstore.features.cart.domain.entities.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun removeFromCart(isbn: Long)
    suspend fun updateQuantity(isbn: Long, quantity: Int)
    suspend fun clearCart()
}
