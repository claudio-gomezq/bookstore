package com.cgomezq.bookstore.features.books.di

import com.cgomezq.bookstore.features.books.data.api.BooksApi
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<BooksApi> {
        get<Retrofit>().create(BooksApi::class.java)
    }
}