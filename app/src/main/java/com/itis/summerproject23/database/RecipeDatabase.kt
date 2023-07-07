package com.itis.summerproject23.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class, FavoriteRecipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}