package com.example.newsapp.data.usecases.appEntry

import com.example.newsapp.domain.manager.LocalUserManager

class SaveEntryPoint(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke() {
        localUserManager.saveEntryPoint()
    }

}