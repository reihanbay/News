package com.submission.news.di


import com.submission.news.BuildConfig
import com.submission.news.data.HeaderInterceptor
import com.submission.news.data.news.NewsDataStore
import com.submission.news.data.news.NewsRepository
import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.data.news.remote.NewsApi
import com.submission.news.data.news.remote.NewsApiClient
import com.submission.news.ui.home.NewsViewModel
import com.submission.news.utils.api.ApiService
import com.submission.news.utils.api.Endpoint
import com.submission.news.utils.api.OkHttpClientFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val newsModule = module {
    single {
        val okHttpClient = OkHttpClientFactory.create(
            showDebugLog = BuildConfig.DEBUG,
            interceptors = HeaderInterceptor()
        )
        ApiService.createReactiveService(
            NewsApiClient::class.java,
            okHttpClient,
            get(named(Endpoint.BASE_URL))
        )
    }

    single { NewsApi(get()) }
    single<NewsRepository> { NewsDataStore(get()) }
    viewModel { NewsViewModel(get()) }
}