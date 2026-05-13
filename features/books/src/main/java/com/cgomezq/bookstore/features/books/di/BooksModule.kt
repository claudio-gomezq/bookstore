package com.cgomezq.bookstore.features.books.di

import com.cgomezq.bookstore.features.books.data.api.BooksApi
import com.cgomezq.bookstore.features.books.data.repositories.ActualBooksRepository
import com.cgomezq.bookstore.features.books.domain.repositories.BooksRepository
import com.cgomezq.bookstore.features.books.domain.usecases.GetBookDetail
import com.cgomezq.bookstore.features.books.domain.usecases.GetBooks
import com.cgomezq.bookstore.features.books.ui.viewmodels.BookDetailViewmodel
import com.cgomezq.bookstore.features.books.ui.viewmodels.BookListViewmodel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<BooksApi> {
        get<Retrofit>().create(BooksApi::class.java)
    }
}

val domainModule = module {
    factory { ActualBooksRepository(get()) } bind BooksRepository::class
    factory { GetBooks(get()) }
    factory { GetBookDetail(get()) }
}

val viewModelModule = module {
    viewModelOf(::BookListViewmodel)
    viewModel { params -> BookDetailViewmodel(get(), get(), get(), params.get()) }
}

val booksModule = module {
    includes(dataModule, domainModule, viewModelModule)
}
