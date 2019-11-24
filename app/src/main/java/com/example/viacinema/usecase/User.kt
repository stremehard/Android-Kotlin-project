package com.example.viacinema.usecase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstname: String,
    val lastname: String,
    val birthday: String,
    val address: String,
    val username: String,
    val email: String,
    val password: String
)
