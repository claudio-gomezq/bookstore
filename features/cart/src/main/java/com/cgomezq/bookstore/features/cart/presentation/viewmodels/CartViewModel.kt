package com.cgomezq.bookstore.features.cart.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgomezq.bookstore.features.cart.domain.usecases.ClearCart
import com.cgomezq.bookstore.features.cart.domain.usecases.GetCartItems
import com.cgomezq.bookstore.features.cart.domain.usecases.UpdateCartQuantity
import com.cgomezq.bookstore.features.cart.presentation.contract.CartState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class CartViewModel(
    getCartItems: GetCartItems,
    private val updateCartQuantity: UpdateCartQuantity,
    private val clearCart: ClearCart
) : ViewModel() {

    val uiState: StateFlow<CartState> = getCartItems()
        .map { items ->
            if (items.isEmpty()) {
                CartState.ShowingEmptyCart
            } else {
                val totalValue = items.sumOf { it.totalPriceValue }
                val formattedTotal = formatPrice(totalValue)
                
                CartState.ShowingCart(items, formattedTotal)
            }
        }
        .catch { emit(CartState.ShowingError(it.message ?: "Error")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CartState.Loading
        )

    fun onUpdateQuantity(isbn: Long, quantity: Int) {
        viewModelScope.launch {
            updateCartQuantity(isbn, quantity)
        }
    }

    fun onClearCart() {
        viewModelScope.launch {
            clearCart()
        }
    }

    private fun formatPrice(value: Double, currencyCode: String = "$"): String {
        return try {
            val symbols = DecimalFormatSymbols().apply {
                groupingSeparator = '.'
            }
            val formatter = DecimalFormat("#,###", symbols)
            val formattedText = formatter.format(value)
            "$currencyCode $formattedText"
        } catch (_: Exception) {
            "$currencyCode $value"
        }
    }
}
