package com.cgomezq.bookstore.features.books.di

import org.koin.dsl.module


val booksModule = module {
    includes(dataModule, domainModule, viewModelModule)
}