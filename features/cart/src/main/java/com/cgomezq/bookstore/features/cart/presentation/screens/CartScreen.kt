package com.cgomezq.bookstore.features.cart.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cgomezq.bookstore.features.cart.R
import com.cgomezq.bookstore.features.cart.presentation.components.CartItemRow
import com.cgomezq.bookstore.features.cart.presentation.components.CartSummary
import com.cgomezq.bookstore.features.cart.presentation.contract.CartState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    state: CartState,
    onQuantityChange: (Long, Int) -> Unit,
    onClearCart: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.cart_title)) })
        },
        contentWindowInsets = WindowInsets()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (state) {
                CartState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is CartState.ShowingCart -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(state.items) { item ->
                                CartItemRow(
                                    item = item,
                                    onQuantityChange = { newQty -> onQuantityChange(item.isbn, newQty) }
                                )
                                HorizontalDivider()
                            }
                        }
                        
                        CartSummary(
                            totalPrice = state.totalPrice,
                            onClearCart = onClearCart
                        )
                    }
                }

                CartState.ShowingEmptyCart -> {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.cart_empty_message)
                    )
                }

                is CartState.ShowingError -> {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = state.message
                    )
                }
            }
        }
    }
}
