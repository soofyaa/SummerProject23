package com.itis.summerproject23

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.itis.summerproject23.database.RecipeDatabase
import com.itis.summerproject23.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private var adapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavoriteBinding.bind(view)

        val recipesDatabase = context?.let {
            Room.databaseBuilder(it, RecipeDatabase::class.java, "database-recipes")
                .allowMainThreadQueries()
                .build()
                .recipeDao()
        }

        val bundle = Bundle()

        if (recipesDatabase != null) {
            adapter = RecipeAdapter(recipesDatabase.getAllFavorite(),
                Glide.with(this)) { recipe ->
                bundle.putInt("ID", recipe.id)
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_recipePageFragment, bundle)
            }
        }
        binding.rvFavorite.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}