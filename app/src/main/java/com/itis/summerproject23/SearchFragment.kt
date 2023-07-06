package com.itis.summerproject23

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.itis.summerproject23.database.Recipe
import com.itis.summerproject23.database.RecipeDatabase
import com.itis.summerproject23.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private val PREFS_NAME = "MyPrefs"
    private val PREF_FIRST_RUN = "isFirstRun"

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View? {

        val sharedPrefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstRun = sharedPrefs.getBoolean(PREF_FIRST_RUN, true)

        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = view.findViewById(R.id.sv_recipe)
        recyclerView = view.findViewById(R.id.rv_recipe)

        val recipesDatabase = context?.let {
            Room.databaseBuilder(it, RecipeDatabase::class.java, "recipes")
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

        val bundle = Bundle()

        if (recipesDatabase != null) {
            adapter = RecipeAdapter(
                recipesDatabase.recipeDao().getAllRecipes(),
                Glide.with(this)
            ) { recipe ->
                bundle.putInt("ID", recipe.id)
                findNavController().navigate(
                    R.id.action_searchFragment_to_recipePageFragment,
                    bundle
                )
            }
        }

        recyclerView.adapter = adapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }

            private fun filter(text: String?) {
                val filteredList: ArrayList<Recipe> = ArrayList()

                if (recipesDatabase != null) {
                    for (item in recipesDatabase.recipeDao().getAllRecipes()) {
                        if (item.name.toLowerCase().contains(text.toString().toLowerCase())) {

                            filteredList.add(item)
                        }
                    }
                    if (filteredList.isEmpty()) {

                        Toast.makeText(requireActivity(), "No Data Found..", Toast.LENGTH_SHORT)
                            .show()
                    } else {

                        adapter?.updateData(filteredList)
                    }
                }
            }
        })
        return view
    }
}