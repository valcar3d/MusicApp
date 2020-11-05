package com.dimensiva.musicapp.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_accounts")
data class UserAccount(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nickName: String,
    var nombreYapellido: String, var descripcion: String,
    var perfilImagen: Int
)