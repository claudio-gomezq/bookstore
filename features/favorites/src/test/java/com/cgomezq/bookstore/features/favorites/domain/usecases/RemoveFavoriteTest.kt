package com.cgomezq.bookstore.features.favorites.domain.usecases

import com.cgomezq.bookstore.features.favorites.domain.repositories.FavoritesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoveFavoriteTest {
    private val repository: FavoritesRepository = mockk()
    private val removeFavorite = RemoveFavorite(repository)

    @Test
    fun `invoke calls repository removeFavorite`() = runTest {
        coEvery { repository.removeFavorite(1L) } returns Unit
        
        removeFavorite(1L)
        
        coVerify { repository.removeFavorite(1L) }
    }
}
