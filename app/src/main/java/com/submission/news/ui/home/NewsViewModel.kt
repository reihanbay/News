package com.submission.news.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.submission.news.data.news.NewsRepository
import com.submission.news.data.news.model.NewsDataClass
import com.submission.news.utils.api.Base
import com.submission.news.utils.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val repository : NewsRepository) : ViewModel() {

    val getNews = MutableLiveData<Result<Base<NewsDataClass>>>()

    init {
        getNews.value = Result.default()
    }

    fun getNews() {
        getNews.value = Result.loading()
        viewModelScope.launch {
            var result : Base<NewsDataClass>
            try {
                withContext(Dispatchers.IO) {
                    result = repository.getNews()
                }
                if (result.articles.isEmpty()) getNews.value = Result.empty()
                else getNews.value = Result.success(result)
            } catch (e: Throwable) {
                getNews.value = Result.fail(e, e.stackTraceToString(), e.message)
            }
        }
    }
}