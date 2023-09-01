package com.kryptonictest.network

import android.accounts.NetworkErrorException
import android.util.Log
import com.kryptonictest.network.ApiConstants.ACCEPT_TYPE
import com.kryptonictest.network.ApiConstants.CONTENT_TYPE
import com.kryptonictest.network.ApiConstants.HEADER_ACCEPT_TYPE
import com.kryptonictest.network.ApiConstants.HEADER_CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
        request.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE)
            .addHeader(HEADER_ACCEPT_TYPE, ACCEPT_TYPE)

        return chain.proceed(request.build())
    }
}

val loggerInterceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor { message: String ->
        Log.d("httpLoggingInterceptor", message)
    }
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

class NetworkErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            throw NetworkErrorException("Network error occurred")
        }
        return response
    }
}
