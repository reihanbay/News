package com.submission.news.data.news.remote

import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.utils.api.Base

class NewsApi(val api: NewsApiClient) : NewsApiClient {
    override suspend fun getNews(): Base<NewsDataClass> {
        return api.getNews()
    }
}