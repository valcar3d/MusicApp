package com.dimensiva.musicapp.entity

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_accounts")
data class UserAccount(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nickName: String,
    val nombreYapellido: String, val descripcion: String,
    val perfilImagen: Int
)