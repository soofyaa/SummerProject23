package com.itis.summerproject23

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class SearchFragment : Fragment(R.layout.fragment_search) {
//    private lateinit var searchView: SearchView
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: RecipeAdapter
//    private val originalList: List<Recipe> = RecipeRepository.list
//    private lateinit var menuItem: MenuItem
//    private lateinit var  searchList: List<Recipe>
//    private lateinit var recipeClass: Recipe
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_search, container, false)
//
//        searchView = view.findViewById(R.id.sv_recipe)
//        recyclerView = view.findViewById(R.id.rv_recipe)
//
//        adapter = RecipeAdapter(
//            originalList,
//            Glide.with(this)
//        )
//        recyclerView.adapter = adapter
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                filter(newText)
//                return false
//            }
//            private fun filter(text: String?) {
//                val filteredlist: ArrayList<Recipe> = ArrayList()
//
//                for (item in originalList) {
//                    if (item.name.toLowerCase().contains(text.toString().toLowerCase())) {
//
//                        filteredlist.add(item)
//                    }
//                }
//                if (filteredlist.isEmpty()) {
//
//                    Toast.makeText(requireActivity(), "No Data Found..", Toast.LENGTH_SHORT).show()
//                } else {
//
//                    adapter.updateData(filteredlist)
//                }
//            }
//
//        })
//        return view
//    }

}