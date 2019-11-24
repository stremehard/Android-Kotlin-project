package com.example.viacinema.usecase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE username LIKE :username")
    fun getUser(username: String): User?
}