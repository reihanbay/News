package com.submission.news.data.news.remote

import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.utils.api.Base
import retrofit2.http.GET

interface NewsApiClient {

    @GET("everything?domains=wsj.com")
    suspend fun getNews() : Base<NewsDataClass>

}