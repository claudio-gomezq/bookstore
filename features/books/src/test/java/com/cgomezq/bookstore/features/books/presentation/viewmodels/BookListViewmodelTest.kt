package com.cgomezq.bookstore.features.books.presentation.viewmodels

import app.cash.turbine.test
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price
import com.cgomezq.bookstore.features.books.domain.exceptions.EmptyBookListException
import com.cgomezq.bookstore.features.books.domain.usecases.GetBooks
import com.cgomezq.bookstore.features.books.presentation.contract.BookListState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookListViewmodelTest {

    private val getBooks: GetBooks = mockk()
    private lateinit var viewModel: BookListViewmodel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is initialized, it should load books`() = runTest {
        // Given
        val books = listOf(
            Book(
                isbn = 1234567890L,
                title = "Test Book",
                author = "Test Author",
                summary = "Test Summary",
                coverUrl = "https://example.com/cover.jpg",
                isFavorite = false,
                price = Price(10000.0, "CLP", "$10.000")
            )
        )
        coEvery { getBooks() } returns books

        // When
        viewModel = BookListViewmodel(getBooks)

        // Then
        viewModel.uiState.test {
            assertEquals(BookListState.Loading, awaitItem())
            assertEquals(BookListState.ShowingBookList(books), awaitItem())
        }
    }

    @Test
    fun `when getBooks throws EmptyBookListException, it should show empty state`() = runTest {
        // Given
        coEvery { getBooks() } throws EmptyBookListException()

        // When
        viewModel = BookListViewmodel(getBooks)

        // Then
        viewModel.uiState.test {
            assertEquals(BookListState.Loading, awaitItem())
            assertEquals(BookListState.ShowingEmptyList, awaitItem())
        }
    }
}
