package com.example.newsapp.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    //#1 main start root route
    object AppStartNavigation : Route(route = "appStartNavigation")

    //child routes
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    //#2 inner news app screen route
    object NewsNavigation : Route(route = "newsNavigation")

    //child routes
    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")


}
