package com.cgomezq.bookstore.features.books.domain.usecases

import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price
import com.cgomezq.bookstore.features.books.domain.exceptions.EmptyBookListException
import com.cgomezq.bookstore.features.books.domain.repositories.BooksRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetBooksTest {

    private val repository: BooksRepository = mockk()
    private val getBooks = GetBooks(repository)

    @Test
    fun `when repository returns books, invoke should return them`() = runTest {
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
        coEvery { repository.getBooks() } returns books

        // When
        val result = getBooks()

        // Then
        assertEquals(books, result)
    }

    @Test(expected = EmptyBookListException::class)
    fun `when repository returns empty list, invoke should throw EmptyBookListException`() = runTest {
        // Given
        coEvery { repository.getBooks() } returns emptyList()

        // When
        getBooks()

        // Then throw exception
    }
}
