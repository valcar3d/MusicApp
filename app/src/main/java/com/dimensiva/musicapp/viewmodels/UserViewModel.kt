package com.dimensiva.musicapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dimensiva.musicapp.database.AppDatabase
import com.dimensiva.musicapp.entity.UserAccount
import com.dimensiva.musicapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    val allUsers: LiveData<List<UserAccount>>

    init {
        val usersDao = AppDatabase.getDatabase(application).userAccountDao()
        repository = UserRepository(usersDao!!)
        allUsers = repository.allUsers
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(user: UserAccount) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }
}