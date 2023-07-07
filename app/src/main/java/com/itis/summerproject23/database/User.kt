package com.itis.summerproject23.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var password: String,
    var email: String,
)