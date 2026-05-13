package com.cgomezq.bookstore.features.cart.presentation.viewmodels

import app.cash.turbine.test
import com.cgomezq.bookstore.features.cart.domain.entities.CartItem
import com.cgomezq.bookstore.features.cart.domain.usecases.ClearCart
import com.cgomezq.bookstore.features.cart.domain.usecases.GetCartItems
import com.cgomezq.bookstore.features.cart.domain.usecases.UpdateCartQuantity
import com.cgomezq.bookstore.features.cart.presentation.contract.CartState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {

    private val getCartItems: GetCartItems = mockk()
    private val updateCartQuantity: UpdateCartQuantity = mockk()
    private val clearCart: ClearCart = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when cart is empty then state is ShowingEmptyCart`() = runTest {
        every { getCartItems() } returns flowOf(emptyList())

        val viewModel = CartViewModel(getCartItems, updateCartQuantity, clearCart)

        viewModel.uiState.test {
            assertEquals(CartState.Loading, awaitItem())
            assertEquals(CartState.ShowingEmptyCart, awaitItem())
        }
    }

    @Test
    fun `when cart has items then state is ShowingCart with total price`() = runTest {
        val items = listOf(
            CartItem(1L, "Book 1", "Author 1", "url1", 10.0, "CLP", "$10.00", 2)
        )
        every { getCartItems() } returns flowOf(items)

        val viewModel = CartViewModel(getCartItems, updateCartQuantity, clearCart)

        viewModel.uiState.test {
            assertEquals(CartState.Loading, awaitItem())
            val state = awaitItem() as CartState.ShowingCart
            assertEquals(items, state.items)
            assertTrue(state.totalPrice.contains("20"))
        }
    }

    @Test
    fun `onUpdateQuantity calls use case`() = runTest {
        every { getCartItems() } returns flowOf(emptyList())
        coEvery { updateCartQuantity(1L, 5) } returns Unit

        val viewModel = CartViewModel(getCartItems, updateCartQuantity, clearCart)
        viewModel.onUpdateQuantity(1L, 5)
        
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { updateCartQuantity(1L, 5) }
    }

    @Test
    fun `onClearCart calls use case`() = runTest {
        every { getCartItems() } returns flowOf(emptyList())
        coEvery { clearCart() } returns Unit

        val viewModel = CartViewModel(getCartItems, updateCartQuantity, clearCart)
        viewModel.onClearCart()
        
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { clearCart() }
    }
}
