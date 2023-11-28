package com.example.newsapp.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String, val description: String, @DrawableRes val image: Int
)

val Pages = listOf(
    Page(
        title = "Latest News",
        description = "here are many variations of passages of Lorem Ipsum available",
        image = R.drawable.ic_welcome_icon1
    ),
    Page(
        title = "Top headlines",
        description = "here are many variations of passages of Lorem Ipsum available",
        image = R.drawable.ic_welcome_icon2
    ),
    Page(
        title = "Top 10 News",
        description = "here are many variations of passages of Lorem Ipsum available",
        image = R.drawable.ic_welcome_icon3
    )
)