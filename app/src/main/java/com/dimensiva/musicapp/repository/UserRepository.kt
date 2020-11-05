package com.dimensiva.musicapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.dimensiva.musicapp.database.dao.UserAccountDao
import com.dimensiva.musicapp.entity.UserAccount


class UserRepository(private val userAccountDao: UserAccountDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<UserAccount>> = userAccountDao.getAllUsers()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: UserAccount) {
        userAccountDao.insert(user)
    }


}