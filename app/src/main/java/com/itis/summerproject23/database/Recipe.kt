package com.itis.summerproject23.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name : String,
    var ingredients : String,
    var text : String,
    var url: String
)
