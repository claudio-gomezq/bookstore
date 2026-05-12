package com.cgomezq.bookstore.core.network.di

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    single<Converter.Factory> { (json: Json) ->
        json.asConverterFactory(contentType = "application/json; charset=UTF8".toMediaType())
    }

    single<Retrofit> { (baseUrl: String, converter: Converter.Factory, httpClient: OkHttpClient) ->
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .client(httpClient)
            .build()
    }
}