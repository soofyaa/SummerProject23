package com.itis.summerproject23

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.summerproject23.databinding.FragmentRecipePageBinding

class RecipePageFragment : Fragment(R.layout.fragment_recipe_page) {

    private var binding: FragmentRecipePageBinding? = null
    private var adapter: RecipeAdapter? = null
    private val options: RequestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipePageBinding.bind(view)
        adapter = RecipeAdapter(RecipeRepository.list, Glide.with(this)) {}

        val recipe = RecipeRepository.getItemById(arguments?.getInt(ARG_ID))


        binding?.run {
            tvTitle.text = recipe?.name.toString()
            tvIngredients.text = recipe?.ingredients.toString()
            tvText.text = recipe?.text.toString()
            Glide.with(root)
                .load(recipe?.url)
                .placeholder(R.drawable.loading)
                .apply(options)
                .error(R.drawable.image_not_available)
                .into(ivImage)
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_recipePageFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val ARG_ID = "ARG_ID"

        fun createBundle(idRecipe: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(ARG_ID, idRecipe)
            return bundle
        }
    }
}