package com.cgomezq.bookstore.features.books.domain.repositories

import com.cgomezq.bookstore.features.books.domain.entities.Book

interface BooksRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBookDetail(isbn: Long): Book
}