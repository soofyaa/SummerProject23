package com.itis.summerproject23

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.itis.summerproject23.database.Recipe
import com.itis.summerproject23.database.RecipeDatabase
import com.itis.summerproject23.databinding.FragmentAddBinding

class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAddBinding.bind(view)
        binding.btnAdd.setOnClickListener {
            with(binding) {
                val name = etName.text.toString()
                val text = etText.text.toString()
                val ingredients = etIngredients.text.toString()
                val url = etUrl.text.toString()

                if (name.isNotEmpty() && text.isNotEmpty() && ingredients.isNotEmpty() && url.isNotEmpty()) {
                    addToBase(name, text, ingredients, url)
                    hideKeyboard()
                    Snackbar.make(view, getString(R.string.recipe_is_saved), Snackbar.LENGTH_LONG)
                        .apply { setAnchorView(R.id.bnv_main) }.show()
                } else {
                    hideKeyboard()
                    Snackbar.make(view, getString(R.string.need_all_data), Snackbar.LENGTH_LONG)
                        .apply { setAnchorView(R.id.bnv_main) }.show()
                }
                etName.text.clear()
                etText.text.clear()
                etIngredients.text.clear()
                etUrl.text.clear()
            }
        }
    }

    private fun addToBase(
        nameGet: String,
        textGet: String,
        ingredientsGet: String,
        urlGet: String,
    ) {
        val newRecipes = Recipe(
            name = nameGet,
            text = textGet,
            ingredients = ingredientsGet,
            url = urlGet,
            isFavorite = 0
        )
        context?.let {
            Room.databaseBuilder(it, RecipeDatabase::class.java, "database-recipes")
                .allowMainThreadQueries()
                .build()
                .recipeDao()
        }?.insertRecipe(newRecipes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}