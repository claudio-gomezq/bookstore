package com.cgomezq.bookstore.features.favorites.presentation.viewmodels

import app.cash.turbine.test
import com.cgomezq.bookstore.features.favorites.domain.entities.FavoriteBook
import com.cgomezq.bookstore.features.favorites.domain.usecases.GetFavorites
import com.cgomezq.bookstore.features.favorites.domain.usecases.RemoveFavorite
import com.cgomezq.bookstore.features.favorites.presentation.contract.FavoritesState
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
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest {

    private val getFavorites: GetFavorites = mockk()
    private val removeFavorite: RemoveFavorite = mockk()
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
    fun `when favorites are empty then state is ShowingEmptyList`() = runTest {
        every { getFavorites() } returns flowOf(emptyList())

        val viewModel = FavoritesViewModel(getFavorites, removeFavorite)

        viewModel.uiState.test {
            assertEquals(FavoritesState.Loading, awaitItem())
            assertEquals(FavoritesState.ShowingEmptyList, awaitItem())
        }
    }

    @Test
    fun `when favorites are present then state is ShowingFavorites`() = runTest {
        val books = listOf(
            FavoriteBook(1L, "Title", "Author", "url", "$10")
        )
        every { getFavorites() } returns flowOf(books)

        val viewModel = FavoritesViewModel(getFavorites, removeFavorite)

        viewModel.uiState.test {
            assertEquals(FavoritesState.Loading, awaitItem())
            assertEquals(FavoritesState.ShowingFavorites(books), awaitItem())
        }
    }

    @Test
    fun `when remove favorite is called then use case is invoked`() = runTest {
        every { getFavorites() } returns flowOf(emptyList())
        coEvery { removeFavorite(1L) } returns Unit

        val viewModel = FavoritesViewModel(getFavorites, removeFavorite)
        viewModel.onRemoveFavorite(1L)

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { removeFavorite(1L) }
    }
}
