package com.submission.news.data

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = "6ca07e9973d64ca88cf3a1aacc65427e"
        val original = chain.request()

        val request = original.newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        return chain.proceed(request)
    }
}