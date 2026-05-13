package com.cgomezq.bookstore.core.network.di

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    single<String> {
        "https://my-json-server.typicode.com/claudio-gomezq/books/"
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    single<Converter.Factory> {
        get<Json>().asConverterFactory(contentType = "application/json; charset=UTF8".toMediaType())
    }

    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(get<String>())
            .addConverterFactory(get())
            .client(get())
            .build()
    }
}