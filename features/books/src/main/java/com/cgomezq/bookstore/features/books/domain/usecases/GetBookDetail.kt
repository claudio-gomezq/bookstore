package com.cgomezq.bookstore.features.books.domain.usecases

import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.repositories.BooksRepository

class GetBookDetail(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(isbn: Long): Book {
        return repository.getBookDetail(isbn)
    }
}