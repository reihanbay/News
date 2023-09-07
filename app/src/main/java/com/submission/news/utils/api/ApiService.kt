package com.submission.news.utils.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    fun <T> createReactiveService(
        serviceClass : Class<T>,
        okHttp : OkHttpClient,
        baseUrl: String
    ) : T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }
}