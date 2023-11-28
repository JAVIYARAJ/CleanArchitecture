package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.data.manager.LocalUserImpl
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.data.usecases.appEntry.AppEntryUseCases
import com.example.newsapp.data.usecases.appEntry.ReadAppEntry
import com.example.newsapp.data.usecases.appEntry.SaveEntryPoint
import com.example.newsapp.data.usecases.news.GetNews
import com.example.newsapp.data.usecases.news.NewsUseCases
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserImpl(application)

    @Provides
    fun provideAppEntryUseCases(
        userManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(userManager),
        saveEntryPoint = SaveEntryPoint(userManager)
    )

    @Provides
    fun provideNewsApiClient(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }

    @Provides
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases = NewsUseCases(getNews = GetNews(newsRepository = newsRepository))

}