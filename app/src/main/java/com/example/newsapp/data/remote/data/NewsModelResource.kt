package com.example.newsapp.data.remote.data

import com.example.newsapp.domain.model.Article

data class NewsModelResource(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)