package com.cgomezq.bookstore.features.cart.data.repositories

import com.cgomezq.bookstore.core.common.contract.CartManagerRepository
import com.cgomezq.bookstore.features.cart.data.database.CartDao
import com.cgomezq.bookstore.features.cart.data.entities.CartItemEntity
import com.cgomezq.bookstore.features.cart.domain.entities.CartItem
import com.cgomezq.bookstore.features.cart.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ActualCartRepository(
    private val cartDao: CartDao
) : CartRepository, CartManagerRepository {

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getCartItems().map { entities ->
            entities.map { it.toCartItem() }
        }
    }

    override suspend fun removeFromCart(isbn: Long) {
        cartDao.deleteCartItem(isbn)
    }

    override suspend fun updateQuantity(isbn: Long, quantity: Int) {
        if (quantity <= 0) {
            cartDao.deleteCartItem(isbn)
        } else {
            val existing = cartDao.getCartItem(isbn)
            existing?.let {
                cartDao.updateCartItem(it.copy(quantity = quantity))
            }
        }
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }

    override suspend fun addToCart(
        isbn: Long,
        title: String,
        author: String,
        imageUrl: String,
        priceValue: Double,
        priceCurrency: String,
        priceDisplay: String
    ) {
        val existing = cartDao.getCartItem(isbn)
        if (existing != null) {
            cartDao.updateCartItem(existing.copy(quantity = existing.quantity + 1))
        } else {
            cartDao.insertCartItem(
                CartItemEntity(
                    isbn = isbn,
                    title = title,
                    author = author,
                    imageUrl = imageUrl,
                    priceValue = priceValue,
                    priceCurrency = priceCurrency,
                    priceDisplay = priceDisplay,
                    quantity = 1
                )
            )
        }
    }

    private fun CartItemEntity.toCartItem() = CartItem(
        isbn = isbn,
        title = title,
        author = author,
        coverUrl = imageUrl,
        priceValue = priceValue,
        priceCurrency = priceCurrency,
        priceDisplayValue = priceDisplay,
        quantity = quantity
    )
}
