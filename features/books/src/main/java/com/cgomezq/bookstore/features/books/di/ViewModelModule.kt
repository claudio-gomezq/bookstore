package com.cgomezq.bookstore.features.books.di

import com.cgomezq.bookstore.features.books.ui.viewmodels.BookListViewmodel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val viewModelModule = module {
    viewModel<BookListViewmodel>()
}