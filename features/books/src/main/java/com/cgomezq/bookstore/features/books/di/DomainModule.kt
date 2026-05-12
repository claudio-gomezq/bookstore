package com.cgomezq.bookstore.features.books.di

import com.cgomezq.bookstore.features.books.data.repositories.ActualBooksRepository
import com.cgomezq.bookstore.features.books.domain.repositories.BooksRepository
import com.cgomezq.bookstore.features.books.domain.usecases.GetBooks
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    factory { ActualBooksRepository(get()) } bind BooksRepository::class
    factory { GetBooks(get()) }
}