package com.submission.news.di

import com.submission.news.BuildConfig.DEBUG
import com.submission.news.data.HeaderInterceptor
import com.submission.news.utils.api.Endpoint.BASE_URL
import com.submission.news.utils.api.OkHttpClientFactory
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun apiModule(): Module {
    return module {
        single {
            return@single OkHttpClientFactory.create(
                showDebugLog = DEBUG,
                HeaderInterceptor()
            )
        }
        single(named(BASE_URL)) { BASE_URL }
    }
}
