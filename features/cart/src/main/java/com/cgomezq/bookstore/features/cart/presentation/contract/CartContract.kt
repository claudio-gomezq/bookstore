package com.cgomezq.bookstore.features.cart.presentation.contract

import com.cgomezq.bookstore.features.cart.domain.entities.CartItem

sealed interface CartState {
    data object Loading : CartState
    data class ShowingCart(
        val items: List<CartItem>,
        val totalPrice: String
    ) : CartState
    data object ShowingEmptyCart : CartState
    data class ShowingError(val message: String) : CartState
}
