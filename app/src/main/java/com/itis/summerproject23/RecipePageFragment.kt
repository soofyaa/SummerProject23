package com.itis.summerproject23

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.itis.summerproject23.database.Recipe
import com.itis.summerproject23.database.RecipeDatabase
import com.itis.summerproject23.databinding.FragmentRecipePageBinding

class RecipePageFragment : Fragment(R.layout.fragment_recipe_page) {

    private var binding: FragmentRecipePageBinding? = null
    private val options: RequestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    private val PREFS_NAME = "MyPrefs"
    private val PREF_FIRST_RUN = "isFirstRun"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstRun = sharedPrefs.getBoolean(PREF_FIRST_RUN, true)

        binding = FragmentRecipePageBinding.bind(view)
        val recipesDatabase = context?.let {
            Room.databaseBuilder(it, RecipeDatabase::class.java, "database-recipes")
                .allowMainThreadQueries()
                .build()
        }

        if (isFirstRun) {
            if (recipesDatabase != null) {
                populateRecipes(recipesDatabase)
            }
            val editor = sharedPrefs.edit()
            editor.putBoolean(PREF_FIRST_RUN, false)
            editor.apply()
        }

        val id = arguments?.getInt("ID")
        val recipe = findRecipeById(recipesDatabase, id)

        binding?.run {
            Glide.with(root)
                .load(recipe?.url)
                .placeholder(R.drawable.loading)
                .apply(options)
                .error(R.drawable.image_not_available)
                .into(ivImage)
            tvTitle.text = recipe?.name
            tvIngredients.text = recipe?.ingredients
            tvText.text = recipe?.text

            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_recipePageFragment_to_searchFragment)
            }

            swAddToFavorite.isChecked = recipe?.isFavorite != 0

            swAddToFavorite.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    recipe?.isFavorite = 1
                    if (recipe != null) recipesDatabase?.recipeDao()?.updateRecipe(recipe)
                    Snackbar.make(view, getString(R.string.recipe_added_to_favorite), Snackbar.LENGTH_LONG)
                        .apply { setAnchorView(R.id.bnv_main) }.show()
                } else {
                    recipe?.isFavorite = 0
                    if (recipe != null) recipesDatabase?.recipeDao()?.updateRecipe(recipe)
                    Snackbar.make(view, getString(R.string.recipe_removed_from_favorite), Snackbar.LENGTH_LONG)
                        .apply { setAnchorView(R.id.bnv_main) }.show()
                }

                val imm = requireContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(swAddToFavorite.windowToken, 0)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun findRecipeById(recipeDatabase: RecipeDatabase?, idRecipe : Int?) : Recipe? {
         return recipeDatabase?.recipeDao()?.getAllRecipes()?.find {
             it.id == idRecipe
         }
    }
}
