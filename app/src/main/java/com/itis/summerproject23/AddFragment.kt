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
        val binding = FragmentAddBinding.bind(view)

        binding.btmAdd.setOnClickListener {
            with(binding) {
                addToBase(
                    etName.text.toString(), etText.text.toString(),
                    etIngredients.text.toString(), etURL.text.toString()
                )
            }

            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.btmAdd.windowToken, 0)

            Snackbar.make(view, "Рецепт успешно сохранен!", Snackbar.LENGTH_LONG)
                .apply { setAnchorView(R.id.bnv_main) }.show()
        }
    }

    private fun addToBase(

        nameGet: String,
        textGet: String,
        ingredientsGet: String,
        urlGet: String

    ) {
        val newRecipes = Recipe(

            name = nameGet,
            text = textGet,
            ingredients = ingredientsGet,
            url = urlGet

        )
        context?.let {

            Room.databaseBuilder(it, RecipeDatabase::class.java, "database-recipes")
                .allowMainThreadQueries()
                .build()
                .recipeDao()

        }?.insertRecipe(newRecipes)
    }
}