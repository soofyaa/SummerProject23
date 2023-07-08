package com.itis.summerproject23.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users_table")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM users_table WHERE id LIKE :inputId")
    fun getUserById(inputId: Int): User

    @Insert
    fun insertUser(user: User)


}