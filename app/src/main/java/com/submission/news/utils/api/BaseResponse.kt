package com.submission.news.utils.api

import com.google.gson.annotations.SerializedName

data class Base<T>(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<T>,

    @field:SerializedName("status")
    val status: String? = null
)