package com.submission.news.data.news

import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.data.news.remote.NewsApi
import com.submission.news.utils.api.Base

class NewsDataStore(val apiClient: NewsApi) : NewsRepository {
    override suspend fun getNews(): Base<NewsDataClass> {
        return apiClient.getNews()
    }

}