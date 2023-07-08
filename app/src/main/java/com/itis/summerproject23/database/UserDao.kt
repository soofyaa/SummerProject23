package com.itis.summerproject23.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM users_table")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM users_table WHERE id LIKE :inputId")
    fun getUserById(inputId: Int): User

    @Insert
    fun insertUser(user: User)
    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users_table WHERE name = :username AND password = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): User?


}