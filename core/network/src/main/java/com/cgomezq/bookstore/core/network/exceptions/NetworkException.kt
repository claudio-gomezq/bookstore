package com.cgomezq.bookstore.core.network.exceptions

import retrofit2.Response

data class NetworkException(
    override val message: String
): Throwable(message)


suspend fun <T> handleResponse(message: String, handler:  suspend () -> Response<T>): T{
    val response = handler()
    val body = response.body()
    if (!response.isSuccessful || body == null){
        throw NetworkException(message)
    }
    return body
}