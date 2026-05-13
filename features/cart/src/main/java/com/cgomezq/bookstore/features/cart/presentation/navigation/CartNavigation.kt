package com.cgomezq.bookstore.features.cart.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cgomezq.bookstore.features.cart.presentation.screens.CartScreen
import com.cgomezq.bookstore.features.cart.presentation.viewmodels.CartViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object CartDestination

fun NavGraphBuilder.cartNavigation() {
    composable<CartDestination> {
        val viewmodel = koinViewModel<CartViewModel>()
        val state by viewmodel.uiState.collectAsStateWithLifecycle()
        CartScreen(
            state = state,
            onQuantityChange = viewmodel::onUpdateQuantity,
            onClearCart = viewmodel::onClearCart
        )
    }
}
