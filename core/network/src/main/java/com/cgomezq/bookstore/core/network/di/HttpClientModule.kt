package com.cgomezq.bookstore.core.network.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val httpClientModule = module {
    single<OkHttpClient> {
        val builder = OkHttpClient.Builder()
        getAll<Interceptor>().forEach {
            builder.addInterceptor(it)
        }
        builder.build()
    }
}