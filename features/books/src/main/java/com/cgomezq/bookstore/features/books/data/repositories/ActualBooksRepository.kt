package com.cgomezq.bookstore.features.books.data.repositories

import com.cgomezq.bookstore.core.network.exceptions.handleResponse
import com.cgomezq.bookstore.features.books.data.api.BooksApi
import com.cgomezq.bookstore.features.books.data.models.BookModel
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price
import com.cgomezq.bookstore.features.books.domain.repositories.BooksRepository

class ActualBooksRepository(
    private val booksApi: BooksApi
) : BooksRepository {

    override suspend fun getBooks(): List<Book> {
        val response = handleResponse("Error getting books") {
            booksApi.getBooks()
        }
        return response.map { book -> book.toBook(isFavorite = false) }
    }

    override suspend fun getBookDetail(isbn: Long): Book {
        val response = handleResponse("Error getting book detail") {
            booksApi.getBookDetail(isbn)
        }
        return response.toBook(isFavorite = false)
    }

    private fun BookModel.toBook(isFavorite: Boolean): Book {
        return Book(
            isbn = id,
            title = title,
            author = author,
            summary = summary,
            coverUrl = image,
            isFavorite = isFavorite,
            price = Price(
                value = priceValue.toDouble(),
                currency = priceCurrency,
                displayValue = priceDisplayValue
            )
        )
    }
}