package com.submission.news.utils.api

import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    private const val DEFAULT_MAX_REQUEST = 30

    fun create(
        showDebugLog: Boolean,
        interceptors : Interceptor? = null
    ) : OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(120,TimeUnit.SECONDS)
            .connectTimeout(120,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        if (interceptors != null) {
            builder.addInterceptor(interceptors)
        }

        if (showDebugLog) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor).build()
        }

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = DEFAULT_MAX_REQUEST
        builder.dispatcher(dispatcher)

        return builder.build()
    }
}