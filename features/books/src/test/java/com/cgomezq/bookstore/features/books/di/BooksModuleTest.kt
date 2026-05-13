package com.cgomezq.bookstore.features.books.di

import androidx.lifecycle.SavedStateHandle
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class BooksModuleTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `verify books module configuration`() {
        booksModule.verify(
            extraTypes = listOf(
                retrofit2.Retrofit::class,
                SavedStateHandle::class,
                Long::class
            )
        )
    }
}
