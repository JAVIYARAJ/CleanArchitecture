package com.example.newsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.usecases.appEntry.AppEntryUseCases
import com.example.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlinx.coroutines.delay


@HiltViewModel
class MainViewModel @Inject constructor(
    entryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition


    private val _startingRoute = mutableStateOf(Route.AppStartNavigation.route)
    val startingRoute: State<String> = _startingRoute

    init {
        entryUseCases.readAppEntry().onEach {
            if (it) {
                _startingRoute.value = Route.NewsNavigation.route
            } else {
                _startingRoute.value = Route.AppStartNavigation.route
            }
            delay(3000)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}