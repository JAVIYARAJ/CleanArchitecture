package com.example.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.onBoarding.OnBoardingScreen
import com.example.newsapp.presentation.onBoarding.components.AppEntryViewModel

@Composable
fun NavGraph(
    startRoute: String
) {
    val controller = rememberNavController()

    NavHost(navController = controller, startDestination = startRoute) {

        //starting root
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: AppEntryViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }

        //home root
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {

            }
            composable(
                route = Route.SearchScreen.route
            ) {

            }
            composable(
                route = Route.BookmarkScreen.route
            ) {

            }
            composable(
                route = Route.DetailsScreen.route
            ) {

            }
        }
    }

}