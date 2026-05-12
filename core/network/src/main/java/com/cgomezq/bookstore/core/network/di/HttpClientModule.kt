package com.cgomezq.bookstore.core.network.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val httpClientModule = module {
    single<Set<Interceptor>> {
        setOf()
    }

    single<OkHttpClient> { (interceptors: Set<Interceptor>) ->
        val builder = OkHttpClient.Builder()
        interceptors.forEach {
            builder.addInterceptor(it)
        }
        builder.build()
    }
}