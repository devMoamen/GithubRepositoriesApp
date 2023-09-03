package com.githubRepository.network

import android.util.Log
import com.githubRepository.network.ApiConstants.ACCEPT_TYPE
import com.githubRepository.network.ApiConstants.AUTHORIZATION
import com.githubRepository.network.ApiConstants.AUTH_TOKEN
import com.githubRepository.network.ApiConstants.CONTENT_TYPE
import com.githubRepository.network.ApiConstants.HEADER_ACCEPT_TYPE
import com.githubRepository.network.ApiConstants.HEADER_CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
        request.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE)
            .addHeader(HEADER_ACCEPT_TYPE, ACCEPT_TYPE)
            .addHeader(AUTHORIZATION, AUTH_TOKEN)

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
