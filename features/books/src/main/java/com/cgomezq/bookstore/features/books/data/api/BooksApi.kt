package com.cgomezq.bookstore.features.books.data.api

import com.cgomezq.bookstore.features.books.data.models.BookModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface BooksApi {

    @GET("books")
    suspend fun getBooks(): Response<List<BookModel>>

    @GET("books/{isbn}")
    suspend fun getBookDetail(@Path("isbn") isbn: Long): Response<BookModel>
}