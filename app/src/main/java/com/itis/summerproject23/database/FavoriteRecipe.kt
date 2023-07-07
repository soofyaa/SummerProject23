package com.itis.summerproject23.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteRecipe (
    @PrimaryKey(autoGenerate = false)
    var id: Int
)