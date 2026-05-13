package com.cgomezq.bookstore.features.books.domain.usecases

import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.exceptions.EmptyBookListException
import com.cgomezq.bookstore.features.books.domain.repositories.BooksRepository

class GetBooks(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(): List<Book> {
        val books = repository.getBooks()
        if (books.isEmpty()) {
            throw EmptyBookListException()
        }
        return books
    }
}