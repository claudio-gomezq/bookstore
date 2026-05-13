package com.cgomezq.bookstore.features.books.data.repositories

import com.cgomezq.bookstore.core.network.exceptions.NetworkException
import com.cgomezq.bookstore.features.books.data.api.BooksApi
import com.cgomezq.bookstore.features.books.data.models.BookModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class ActualBooksRepositoryTest {

    private val booksApi: BooksApi = mockk()
    private val repository = ActualBooksRepository(booksApi)

    @Test
    fun `when getBooks is successful, it should return a list of books`() = runTest {
        // Given
        val bookModels = listOf(
            BookModel(
                id = 1L,
                title = "Title",
                author = "Author",
                image = "url",
                summary = "Summary",
                priceCurrency = "CLP",
                priceValue = 10000,
                priceDisplayValue = "$10.000"
            )
        )
        coEvery { booksApi.getBooks() } returns Response.success(bookModels)

        // When
        val result = repository.getBooks()

        // Then
        assertEquals(1, result.size)
        assertEquals(1L, result[0].isbn)
        assertEquals("Title", result[0].title)
        assertEquals(10000.0, result[0].price.value, 0.0)
    }

    @Test(expected = NetworkException::class)
    fun `when getBooks fails, it should throw NetworkException`() = runTest {
        // Given
        coEvery { booksApi.getBooks() } returns Response.error(404, "".toResponseBody(null))

        // When
        repository.getBooks()
    }

    @Test
    fun `when getBookDetail is successful, it should return the book`() = runTest {
        // Given
        val isbn = 1L
        val bookModel = BookModel(
            id = isbn,
            title = "Title",
            author = "Author",
            image = "url",
            summary = "Summary",
            priceCurrency = "CLP",
            priceValue = 10000,
            priceDisplayValue = "$10.000"
        )
        coEvery { booksApi.getBookDetail(isbn) } returns Response.success(bookModel)

        // When
        val result = repository.getBookDetail(isbn)

        // Then
        assertEquals(isbn, result.isbn)
        assertEquals("Title", result.title)
    }

    @Test(expected = NetworkException::class)
    fun `when getBookDetail fails, it should throw NetworkException`() = runTest {
        // Given
        val isbn = 1L
        coEvery { booksApi.getBookDetail(isbn) } returns Response.error(500, "".toResponseBody(null))

        // When
        repository.getBookDetail(isbn)
    }
}
