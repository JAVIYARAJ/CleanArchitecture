package com.example.newsapp.presentation.onBoarding.components

sealed class OnBoardingAppEvent {

    //list of all on boarding app event that take input from view and send to view model
    object SaveAppEvent : OnBoardingAppEvent()
}
