package com.cgomezq.bookstore

import android.app.Application
import com.cgomezq.bookstore.core.network.di.httpClientModule
import com.cgomezq.bookstore.core.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookStoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BookStoreApp)
            modules(
                httpClientModule,
                networkModule
            )
        }
    }
}