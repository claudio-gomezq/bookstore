package com.cgomezq.bookstore.features.cart.domain.usecases

import com.cgomezq.bookstore.features.cart.domain.repositories.CartRepository

class ClearCart(
    private val repository: CartRepository
) {
    suspend operator fun invoke() = repository.clearCart()
}
