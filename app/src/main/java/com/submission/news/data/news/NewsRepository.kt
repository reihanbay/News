package com.submission.news.data.news

import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.utils.api.Base

interface NewsRepository {
    suspend fun getNews() : Base<NewsDataClass>
}