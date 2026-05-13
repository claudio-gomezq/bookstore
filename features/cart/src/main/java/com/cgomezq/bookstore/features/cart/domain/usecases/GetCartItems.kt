package com.cgomezq.bookstore.features.cart.domain.usecases

import com.cgomezq.bookstore.features.cart.domain.entities.CartItem
import com.cgomezq.bookstore.features.cart.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItems(
    private val repository: CartRepository
) {
    operator fun invoke(): Flow<List<CartItem>> = repository.getCartItems()
}
