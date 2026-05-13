package com.cgomezq.bookstore.features.favorites.data.repositories

import app.cash.turbine.test
import com.cgomezq.bookstore.features.favorites.data.database.FavoritesDao
import com.cgomezq.bookstore.features.favorites.data.entities.FavoriteBookEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ActualFavoritesRepositoryTest {

    private val favoritesDao: FavoritesDao = mockk()
    private val repository = ActualFavoritesRepository(favoritesDao)

    @Test
    fun `getFavorites maps entities to domain models`() = runTest {
        val entities = listOf(
            FavoriteBookEntity(1L, "Title", "Author", "url", "$10")
        )
        every { favoritesDao.getFavorites() } returns flowOf(entities)

        repository.getFavorites().test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals(1L, result[0].isbn)
            assertEquals("Title", result[0].title)
            awaitComplete()
        }
    }

    @Test
    fun `removeFavorite calls dao deleteFavorite`() = runTest {
        coEvery { favoritesDao.deleteFavorite(1L) } returns Unit

        repository.removeFavorite(1L)

        coVerify { favoritesDao.deleteFavorite(1L) }
    }

    @Test
    fun `toggleFavorite inserts when not favorite`() = runTest {
        coEvery { favoritesDao.isFavorite(1L) } returns false
        coEvery { favoritesDao.insertFavorite(any()) } returns Unit

        repository.toggleFavorite(1L, "Title", "Author", "url", "$10")

        coVerify { favoritesDao.insertFavorite(any()) }
    }

    @Test
    fun `toggleFavorite deletes when already favorite`() = runTest {
        coEvery { favoritesDao.isFavorite(1L) } returns true
        coEvery { favoritesDao.deleteFavorite(1L) } returns Unit

        repository.toggleFavorite(1L, "Title", "Author", "url", "$10")

        coVerify { favoritesDao.deleteFavorite(1L) }
    }

    @Test
    fun `isFavorite returns true when dao returns true`() = runTest {
        coEvery { favoritesDao.isFavorite(1L) } returns true

        val result = repository.isFavorite(1L)

        assertEquals(true, result)
    }

    @Test
    fun `isFavorite returns false when dao returns false`() = runTest {
        coEvery { favoritesDao.isFavorite(1L) } returns false

        val result = repository.isFavorite(1L)

        assertEquals(false, result)
    }
}
