package com.dimensiva.musicapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dimensiva.musicapp.entity.UserAccount

@Dao
interface UserAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: UserAccount)

    @Query("SELECT * FROM user_accounts WHERE nickName =:username")
    fun getAccount(username: String?): UserAccount?

    @Query("SELECT * FROM user_accounts")
    fun getAllUsers(): LiveData<List<UserAccount>>

}