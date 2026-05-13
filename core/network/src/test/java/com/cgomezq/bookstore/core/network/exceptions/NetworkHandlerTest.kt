package com.cgomezq.bookstore.core.network.exceptions

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.Response

class NetworkHandlerTest {

    @Test
    fun `handleResponse returns body when response is successful`() = runTest {
        val expectedBody = "Success"
        val response = Response.success(expectedBody)
        
        val result = handleResponse("Error") { response }
        
        assertEquals(expectedBody, result)
    }

    @Test
    fun `handleResponse throws NetworkException when response is unsuccessful`() = runTest {
        val expectedMessage = "Not Found"
        val response = Response.error<String>(404, "".toResponseBody(null))
        
        val exception = assertThrows(NetworkException::class.java) {
            runBlocking { handleResponse<String>(expectedMessage) { response } }
        }
        
        assertEquals(expectedMessage, exception.message)
    }

    @Test
    fun `handleResponse throws NetworkException when body is null`() = runTest {
        val expectedMessage = "Empty Body"
        val response = Response.success<String>(null)
        
        val exception = assertThrows(NetworkException::class.java) {
            runBlocking { handleResponse<String>(expectedMessage) { response } }
        }
        
        assertEquals(expectedMessage, exception.message)
    }
}
