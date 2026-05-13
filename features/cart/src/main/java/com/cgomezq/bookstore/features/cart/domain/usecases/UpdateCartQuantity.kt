package com.cgomezq.bookstore.features.cart.domain.usecases

import com.cgomezq.bookstore.features.cart.domain.repositories.CartRepository

class UpdateCartQuantity(
    private val repository: CartRepository
) {
    suspend operator fun invoke(isbn: Long, quantity: Int) {
        repository.updateQuantity(isbn, quantity)
    }
}
