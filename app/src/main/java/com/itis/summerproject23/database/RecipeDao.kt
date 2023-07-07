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

    @Query("SELECT * FROM favorites")
    fun getAllFavorite(): List<FavoriteRecipe>

    @Insert
    fun insertFavorite(favourite: FavoriteRecipe)

    @Delete
    fun deleteFavorite(favourite: FavoriteRecipe)

    @Query("SELECT * FROM recipes WHERE id LIKE :inputId")
    fun getRecipeById(inputId: Int): Recipe

    @Query("SELECT * FROM favorites WHERE id LIKE :inputId")
    fun getFavoriteByID(inputId: Int): FavoriteRecipe
}