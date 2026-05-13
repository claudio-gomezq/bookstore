package com.cgomezq.bookstore.features.cart.data.repositories

import app.cash.turbine.test
import com.cgomezq.bookstore.features.cart.data.database.CartDao
import com.cgomezq.bookstore.features.cart.data.entities.CartItemEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ActualCartRepositoryTest {

    private val cartDao: CartDao = mockk()
    private val repository = ActualCartRepository(cartDao)

    @Test
    fun `getCartItems maps entities to domain`() = runTest {
        val entities = listOf(
            CartItemEntity(1L, "T1", "A1", "U1", 10.0, "CLP", "$10", 2)
        )
        every { cartDao.getCartItems() } returns flowOf(entities)

        repository.getCartItems().test {
            val items = awaitItem()
            assertEquals(1, items.size)
            assertEquals(1L, items[0].isbn)
            assertEquals(2, items[0].quantity)
            awaitComplete()
        }
    }

    @Test
    fun `updateQuantity updates dao when quantity positive`() = runTest {
        val existing = CartItemEntity(1L, "T1", "A1", "U1", 10.0, "CLP", "$10", 1)
        coEvery { cartDao.getCartItem(1L) } returns existing
        coEvery { cartDao.updateCartItem(any()) } returns Unit

        repository.updateQuantity(1L, 3)

        coVerify { cartDao.updateCartItem(match { it.isbn == 1L && it.quantity == 3 }) }
    }

    @Test
    fun `updateQuantity deletes item when quantity is zero`() = runTest {
        coEvery { cartDao.deleteCartItem(1L) } returns Unit

        repository.updateQuantity(1L, 0)

        coVerify { cartDao.deleteCartItem(1L) }
    }

    @Test
    fun `clearCart calls dao clear`() = runTest {
        coEvery { cartDao.clearCart() } returns Unit

        repository.clearCart()

        coVerify { cartDao.clearCart() }
    }

    @Test
    fun `addToCart increments quantity if already in cart`() = runTest {
        val existing = CartItemEntity(1L, "T1", "A1", "U1", 10.0, "CLP", "$10", 1)
        coEvery { cartDao.getCartItem(1L) } returns existing
        coEvery { cartDao.updateCartItem(any()) } returns Unit

        repository.addToCart(1L, "T1", "A1", "U1", 10.0, "CLP", "$10")

        coVerify { cartDao.updateCartItem(match { it.isbn == 1L && it.quantity == 2 }) }
    }

    @Test
    fun `addToCart inserts new item if not in cart`() = runTest {
        coEvery { cartDao.getCartItem(1L) } returns null
        coEvery { cartDao.insertCartItem(any()) } returns Unit

        repository.addToCart(1L, "T1", "A1", "U1", 10.0, "CLP", "$10")

        coVerify { cartDao.insertCartItem(match { it.isbn == 1L && it.quantity == 1 }) }
    }
}
