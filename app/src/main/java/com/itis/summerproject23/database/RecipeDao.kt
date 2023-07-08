package com.itis.summerproject23.database

import androidx.room.*

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): List<Recipe>

    @Insert
    fun insertRecipe(recipe: Recipe)

    @Update
    fun updateRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes WHERE isFavorite = 1")
    fun getAllFavorite(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id LIKE :inputId")
    fun getRecipeById(inputId: Int): Recipe

    @Query("SELECT * FROM recipes WHERE isFavorite = 1 AND id LIKE :inputId")
    fun getFavoriteById(inputId: Int): Recipe
}