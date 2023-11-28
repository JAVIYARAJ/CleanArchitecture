package com.example.newsapp.domain.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.NewsApi

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val source: String
) : PagingSource<Int, Article>() {


    private var totalNews = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page = page, source = source, token = "")
            totalNews += newsResponse.articles.size
            val articles =
                newsResponse.articles.distinctBy { it.title } //because some many same article are there in response
            LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = if (totalNews == newsResponse.totalResults) null else (page + 1)
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = Throwable(e)
            )
        }
    }
}