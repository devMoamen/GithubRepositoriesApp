package com.kryptonictest.network

import android.util.Log
import com.kryptonictest.network.ApiConstants.ACCEPT_TYPE
import com.kryptonictest.network.ApiConstants.CONTENT_TYPE
import com.kryptonictest.network.ApiConstants.HEADER_ACCEPT_TYPE
import com.kryptonictest.network.ApiConstants.HEADER_CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
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
