package com.cgomezq.bookstore.features.favorites.domain.usecases

import com.cgomezq.bookstore.features.favorites.domain.repositories.FavoritesRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class GetFavoritesTest {
    private val repository: FavoritesRepository = mockk()
    private val getFavorites = GetFavorites(repository)

    @Test
    fun `invoke calls repository getFavorites`() {
        every { repository.getFavorites() } returns flowOf(emptyList())
        
        getFavorites()
        
        verify { repository.getFavorites() }
    }
}
