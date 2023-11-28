package com.example.newsapp.presentation.onBoarding.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.usecases.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppEntryViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    fun onEvent(event: OnBoardingAppEvent) {
        when (event) {
            is OnBoardingAppEvent.SaveAppEvent -> {
                saveEntry()
            }
        }
    }

    private fun saveEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveEntryPoint()
        }
    }
}