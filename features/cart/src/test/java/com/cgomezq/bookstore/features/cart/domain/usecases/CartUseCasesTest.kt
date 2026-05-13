package com.cgomezq.bookstore.features.cart.domain.usecases

import com.cgomezq.bookstore.features.cart.domain.repositories.CartRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CartUseCasesTest {

    private val repository: CartRepository = mockk()

    @Test
    fun `GetCartItems calls repository`() {
        val useCase = GetCartItems(repository)
        every { repository.getCartItems() } returns flowOf(emptyList())

        useCase()

        verify { repository.getCartItems() }
    }

    @Test
    fun `UpdateCartQuantity calls repository`() = runTest {
        val useCase = UpdateCartQuantity(repository)
        coEvery { repository.updateQuantity(1L, 5) } returns Unit

        useCase(1L, 5)

        coVerify { repository.updateQuantity(1L, 5) }
    }

    @Test
    fun `ClearCart calls repository`() = runTest {
        val useCase = ClearCart(repository)
        coEvery { repository.clearCart() } returns Unit

        useCase()

        coVerify { repository.clearCart() }
    }
}
