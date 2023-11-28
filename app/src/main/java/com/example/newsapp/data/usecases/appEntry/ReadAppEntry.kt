package com.example.newsapp.data.usecases.appEntry

import com.example.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readEntry()
    }

}